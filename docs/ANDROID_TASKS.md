# Android 开发任务清单（QiaoTime）

本文档面向 Android 开发者，目标是把 `APP_SPEC.md` 中的设计细化为可执行任务、接口契约与验收标准。已假设使用原生 Android（Kotlin + Jetpack 组件）。

---

## 1. 输入/输出（简短契约）

- 输入：内置在 APK 的 Markdown 文件和图片（assets 或打包到私有数据目录），用户在 UI 上进行浏览、搜索、收藏等操作。
- 输出：本地 SQLite 数据库（recipes, categories, ingredients, steps, favorites, browsing_history, recent_searches），UI 渲染的 Markdown 页面，和 About 页面显示的许可证与上游声明。

错误模式与成功判定：

- 错误：无法加载内置 md 文件、图片文件丢失、数据库写入失败、渲染异常（关键节点需抛出可捕获的错误并上报日志）。
- 成功：在离线设备上渲染含图片的 Markdown，收藏/历史持久化，About 页面展示上游许可说明。

---

## 2. 功能拆解（任务列表）

1. 内容打包与初始化
   - 任务：在构建时把 Markdown 文件和图片按目录结构打包进 APK（建议放在 `assets/recipes/` 结构），并在应用首次启动时从 assets 初始化或直接读取。
   - 接受标准：应用安装后无需网络能直接加载任一内置菜谱且图片正确显示。

2. 本地数据模型与数据库初始化
   - 数据表（建议 schema）：
     - categories (id INTEGER PRIMARY KEY, name TEXT, parent_id INTEGER NULL, order INTEGER, metadata JSON NULL)
     - recipes (id INTEGER PRIMARY KEY, title TEXT, slug TEXT UNIQUE, category_id INTEGER, summary TEXT, md_path TEXT, cover_image_path TEXT, created_at INTEGER, updated_at INTEGER)
     - ingredients (id INTEGER PRIMARY KEY, recipe_id INTEGER, name TEXT, amount TEXT)
     - steps (id INTEGER PRIMARY KEY, recipe_id INTEGER, seq INTEGER, content_md TEXT, image_path TEXT)
     - favorites (id INTEGER PRIMARY KEY, recipe_id INTEGER, added_at INTEGER)
     - browsing_history (id INTEGER PRIMARY KEY, recipe_id INTEGER, viewed_at INTEGER, position INTEGER NULL)
     - recent_searches (id INTEGER PRIMARY KEY, query TEXT, created_at INTEGER)
   - 任务：实现数据库初始化脚本和迁移策略，支持后续内置 DB 的覆盖（versioning）。
   - 接受标准：DB 成功创建且能存储并读取样例数据。

3. Markdown 渲染
   - 任务：在原生 Android 中集成稳定的 Markdown 渲染库（推荐使用 Markwon）。支持图片、表格、代码块、标题层级和内联样式。
   - 图片加载：使用图片库（推荐 Coil 或 Glide）从 APK 的 assets 或应用私有目录加载图片，图片点击可用 PhotoView/大型预览组件全屏查看并支持缩放。
   - 接受标准：渲染结果与原 Markdown 内容一致，图片加载正常，支持常见 markdown 扩展（表格、代码块等）。

4. 浏览记录与收藏
   - 任务：实现收藏添加/删除、浏览历史记录（最近 N 条，去重），并在 UI 中提供管理（清空历史、查看收藏）功能。
   - 接受标准：收藏与历史在应用重启后仍然存在，UI 操作正常。

5. 搜索功能
   - 任务：在原生 Android 中使用 SQLite FTS5（通过 Room + FTS）建立全文索引，支持按标题、食材搜索并按相关性排序。
   - 接受标准：能在离线情况下根据关键字检索到匹配的菜谱，响应时间 < 300ms（小数据集）。

6. 关于页面与许可证展示
   - 任务：实现 About 页面，显示 `doc/ABOUT_FOR_APP.md` 的文案（版本号替换）、`doc/LICENSES.md` 摘要，并提供上游仓库链接与 issue 模板的复制按钮。
   - 接受标准：About 页面信息完整可复制。

7. 内置更新策略（APK 内置内容更新）
   - 说明：App 使用 APK 内置的菜谱作为优先数据源。通过发布新 APK 来更新内置内容。
   - 任务：实现内置资源的 version 标识（如 assets/recipes/version.json），应用启动时比较内置版本与当前已导入版本，必要时覆盖本地数据或提示用户以新版本为准。
   - 接受标准：安装新 APK 后内置内容生效（覆盖或合并策略已按需求实现）。

8. 隐私与媒体可见性
   - 任务：确保图片与 md 文件不被系统媒体扫描器索引。实现方法：
     - 优先直接从 APK 的 assets 读取（不会被媒体扫描器索引）；
     - 若必须在运行时写出图片供渲染使用，请写到 `getFilesDir()` 或 `getCacheDir()` 下，并在写入目录创建 `.nomedia` 文件。
   - 接受标准：应用内图片渲染正常，但图片不会出现在系统相册或文件管理器的媒体视图中。

9. UI/UX 要求
   - 主题：iOS 风格，简约白色配色，卡片 + 淡阴影，圆角控件。
   - 字体：主文字 16sp，标题分别为 20/18/16。
   - 导航：底部 Tab（首页、分类、搜索、收藏、设置）；页面间支持平滑过渡动画。
   - 模块：RecipeList、RecipeDetail（Markdown 渲染）、CategoryList、Search、Favorites、Settings、About。

10. 构建、Lint 与测试

   - 任务：确保使用 Android Gradle 构建（Gradle wrapper），配置 Kotlin 编译选项、ktlint 或 detekt 进行代码风格检查，并为关键逻辑添加单元测试（JUnit/MockK）与 Android Instrumentation 测试（Espresso）。

   - 接受标准：本地构建通过（`./gradlew assembleDebug`），静态检查无严重问题，关键单元测试通过。

---

## 3. POC 指南（快速验证步骤）

目标：在一台 Android 设备上演示离线渲染含图片的 Markdown，且图片不出现在系统相册，验证收藏与历史功能。

步骤：

1. 在 `assets/recipes/sample/` 放入 `sample.md` 与 `image.jpg`。

2. 在原生 Android 项目中使用 Markwon 来渲染 Markdown，并使用 Coil 或 Glide 加载图片。示例依赖（Gradle）：

- Markwon
- Coil 或 Glide
- PhotoView（全屏缩放预览）


3. 在 Activity/Fragment 中从 assets 读取 `sample.md`（例如通过 `application.assets.open("recipes/sample/sample.md")`），交给 Markwon 渲染到 `TextView`/MarkdownView；图片资源可通过 AssetResourceResolver 或自定义 ImageLoader 从 assets 或私有文件夹加载。

4. 重启应用，验证图片仍显示且文件未出现在系统相册（因为资源位于 APK 或私有目录）。

5. 添加收藏并重启，确认收藏存在（使用 Room 保存 `favorites` 表）。

POC 成功判定：离线设备能渲染并显示图片，图片不在系统相册/文件管理器中可见，收藏/历史持久化。

---

## 4. 接口契约（简要）

- API: getRecipe(id) -> Recipe
  - Recipe: { id, title, slug, category_id, summary, md_path, cover_image_path, created_at, updated_at }
- API: searchRecipes(query, opts) -> [RecipeSummary]
- API: addFavorite(recipe_id) -> boolean
- API: removeFavorite(recipe_id) -> boolean
- API: getHistory(limit) -> [HistoryItem]

错误与异常：上述 API 应返回稳定的错误码/异常对象（例如：NOT_FOUND、IO_ERROR、DB_ERROR、PARSE_ERROR），界面层需友好展示并记录日志。

---

## 5. 验收标准（概括）

- 在 Android 设备上离线渲染含图片 Markdown（图片不出现在系统相册/文件管理器）。
- 收藏/历史功能能持久化并在重启后保持。
- About 页面展示上游来源与许可证摘要。
- 基本搜索与分类功能可用，UI 与主题符合设计规范。

---

## 6. 假设与注意事项

- 假设使用原生 Android（Kotlin + Jetpack 组件）；如果团队后续决定使用其他框架，请相应调整实现细节（例如 assets 目录和图片加载策略）。
- 建议使用 Kotlin（协程、Flow）以提升类型安全性与并发模型；建议采用 Jetpack 组件（Room、WorkManager、Navigation、Lifecycle 等）。
- 对第三方内容的使用必须严格遵守其 LICENSE 要求，必要时在 About 页面中保留完整许可证文本或链接。


