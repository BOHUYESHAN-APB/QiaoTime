# 应用规格与开发方案（APP_SPEC）

目标：为荞见食光（QiaoTime）移动端应用制定明确的 Android 开发规范，使得应用可以离线浏览内置菜谱（Markdown + 图片），支持浏览记录、收藏、内置内容通过 APK 更新且不在系统文件管理器/相册中可见，界面风格为 iOS 风格、简约白色配色。

主要需求：

1. 平台与发行
   - 初期只开发 Android（原生 Kotlin + Jetpack 组件）
   - 内置菜谱与图片随 APK 打包（assets 或原始文件夹），后续通过“内置 APK 升级”方式更新
   - 应用不依赖网络，也不需要用户提供外部存储权限

2. 内容封装与可见性
   - Markdown 文档与图片放在应用内部 assets 或 raw 目录
   - 打包后图片和 Markdown 不应被系统媒体扫描器索引，用户在文件管理器和相册中不可见
     - 在 Android 上：将资源放在 APK 内部或私有应用数据目录（files/internal），不要保存至公共可扫描目录
     - 避免将图片写入外部可公开目录；若运行时解压则放到 getFilesDir() 下并为文件名加 ".nomedia" 或创建空的 `.nomedia` 文件夹以阻止媒体扫描

3. 数据模型（SQLite）
   - categories (id, name, parent_id, order, metadata)
   - recipes (id, title, slug, category_id, summary, md_path, cover_image_path, created_at, updated_at)
   - ingredients (id, recipe_id, name, amount, unit)
   - steps (id, recipe_id, seq, content_md, image_path)
   - favorites (id, recipe_id, added_at)
   - browsing_history (id, recipe_id, viewed_at, position)
   - recent_searches (id, query, created_at)

4. 功能清单
   - 离线浏览：渲染 Markdown（支持图片、表格、代码块、标题、内联样式），在原生 Android 中推荐使用 Markwon
   - 收藏：可以添加/取消收藏，本地存储
   - 浏览记录：保存最近浏览记录并可清除
   - 搜索：基于本地 SQLite 的名称/食材搜索
   - 内置更新：通过新版 APK 发布来替换内置菜谱，应用启动时自动使用内置数据库优先级高于旧数据
   - 关于页面：展示内容来源（CookLikeHOC 等）、使用说明、联系客服/反馈方式以及我们向上游提交 issue 的说明和文本模板

5. UI/UX 规范
   - 主题：iOS 风格，白色为主基调，轻柔阴影与圆角卡片
   - 字体与排版：主文字 16sp，标题层级清晰
   - Markdown 渲染：在原生 Android 中推荐使用 Markwon，图片按比例缩放，点击可打开全屏预览（可使用 PhotoView）
   - 导航：采用底部 Tab（首页、分类、搜索、收藏、设置）或侧滑统一入口

6. 安全与隐私
   - 本地数据仅保存在应用私有目录
   - 不收集网络上用户数据

7. 测试与质量门
   - 构建：确保通过 Android Gradle 构建（使用 Gradle wrapper）
   - Lint/Typecheck：使用 ktlint 或 detekt 进行 Kotlin 代码质量检查
   - 单测：使用 JUnit + MockK（或 Mockito）为关键业务逻辑编写单元测试；使用 Espresso 编写必要的 UI Instrumentation 测试

8. 验收标准
   - 在一台 Android 设备上，应用能离线渲染含图片的 Markdown（图片不出现在系统相册/文件管理器）
   - 收藏/历史功能可用且持久化
   - About 页面包含对外使用第三方数据的声明并提供 issue 模板文本

9. 开发者注意事项（附件）
   - 注意遵守上游内容的开源协议（在 About/License 中声明）
   - 第三方组件的许可证必须在 `doc/LICENSES.md` 中列出


