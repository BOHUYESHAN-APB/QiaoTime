# 第三方与内容许可证清单

此文件用于列出本项目所使用的所有第三方开源组件与任何来自外部仓库（例如 CookLikeHOC）的内容许可情况。开发过程中务必完成下列步骤：

- 确认每个第三方库的许可证类型并在此处记录
- 对于来自第三方仓库的 Markdown 内容，保留原作者署名（如许可证要求）并附上原始仓库链接
- 将关键许可证全文复制到 `doc/licenses/` 目录下（例如 `doc/licenses/react-native.txt`）以便打包到应用的 about 页面

建议初始清单（请工程师确认版本与许可证）：

- react-native — MIT
- react-navigation — MIT
- react-native-sqlite-storage — MIT
- react-native-elements — MIT
- react-native-vector-icons — MIT
- CookLikeHOC 内容 — 请检查其仓库根目录 LICENSE（可能为 MIT / APL2 / 其他），并遵守其要求

工作流程说明：
工作流程说明：

1. 在 `doc/licenses/` 中为每个依赖建立一个单独的文件，包含许可证全文与来源链接

2. 在 `doc/LICENSES.md` 中记录每个依赖的版本、许可证类型以及是否已复制许可证全文

3. 在应用的 About 页面与发布页面附上 `LICENSES.md` 的摘要与链接

初始操作清单：

1. 将上述依赖的实际版本写入本文件（工程师确认 package.json 中的版本号）。

2. 从各依赖仓库根目录复制 LICENSE 全文到 `doc/licenses/`，文件命名格式建议为 `{package-name}.txt`。

3. 对于来自 CookLikeHOC 或其他外部仓库的内容，记录具体文件来源路径并在 `doc/licenses/` 中保存上游 LICENSE。

4. 在发布页面（Play Store / 应用内 About）附上 `LICENSES.md` 的摘要和链接，并在 About 页面展示必要的署名信息。

当前占位文件状态：

| 包名 | 占位文件 | 已填充 |


自动抓取 LICENSE 指南（PowerShell 示例，需联网）

```powershell
# 说明：此脚本用于根据 package.json 中列出的依赖，从 npm registry 获取仓库 URL，并尝试下载仓库根目录的 LICENSE 文件。请在运行前备份并在 CI 环境中审查。

$pkgJson = Get-Content -Raw package.json | ConvertFrom-Json

foreach ($dep in $pkgJson.dependencies.GetEnumerator()) {
	$name = $dep.Key
	$info = Invoke-RestMethod -Uri "https://registry.npmjs.org/$name" -UseBasicParsing
	$repo = $info.repository.url -replace '^git\+','' -replace '\.git$',''
	if ($repo) {
		Write-Host "Fetching LICENSE for $name from $repo"
		# 只示例：尝试下载 LICENSE 文件（需根据 repo host 调整）
	}
}

```

注意：自动化不会总是准确，部分 package.json 中没有 repository 字段或仓库是私有的；推荐手动核对并将许可证全文粘贴到 `doc/licenses/` 中。

