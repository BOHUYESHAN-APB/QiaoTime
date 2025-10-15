# 荞见食光 (QiaoTime)

一款专注于美食菜谱的React Native应用，特别整合了老乡鸡菜谱和荞麦美食，支持Windows和Android平台。

## 功能特点

- 📱 跨平台支持：Windows和Android
- 🍗 老乡鸡菜谱：基于《老乡鸡菜品溯源报告》整理的官方菜谱（[原始仓库链接，克隆重复部分参考原始开源协议遵循](https://github.com/Gar-b-age/CookLikeHOC/)）
- 🌾 荞麦美食：健康营养的荞麦制作美食
- 🔍 智能搜索：支持菜谱名称和食材搜索
- ❤️ 收藏功能：收藏喜欢的菜谱
- 📱 离线访问：本地SQLite数据库存储

## 技术栈

- React Native
- React Navigation
- SQLite (react-native-sqlite-storage)
- React Native Elements
- React Native Vector Icons

## 项目结构

```
QiaoTime/
├── src/
│   ├── components/        # 通用组件
│   ├── database/          # 数据库相关
│   │   └── DatabaseContext.js  # 数据库上下文
│   ├── navigation/        # 导航相关
│   │   └── AppNavigator.js     # 应用导航器
│   ├── screens/           # 页面组件
│   │   ├── HomeScreen.js       # 首页
│   │   ├── RecipeDetailScreen.js  # 菜谱详情
│   │   ├── CategoryScreen.js   # 分类页面
│   │   ├── SearchScreen.js     # 搜索页面
│   │   ├── FavoritesScreen.js  # 收藏页面
│   │   ├── SettingsScreen.js   # 设置页面
│   │   ├── LaoXiangJiScreen.js # 老乡鸡页面
│   │   └── QiaoMaiScreen.js    # 荞麦美食页面
│   └── utils/             # 工具函数
│       └── DataImportScreen.js # 数据导入工具
├── assets/                # 资源文件
│   └── images/            # 图片资源
├── App.js                 # 应用入口
└── package.json           # 项目配置
```

## 安装和运行

### 环境要求

- Node.js >= 14
- React Native CLI
- Android Studio (用于Android开发)
- Visual Studio (用于Windows开发)

### 安装依赖

```bash
npm install
```

### Android运行

```bash
# 启动Metro服务器
npx react-native start

# 运行Android应用
npx react-native run-android
```

### Windows运行

```bash
# 启动Metro服务器
npx react-native start

# 运行Windows应用
npx react-native run-windows
```

## 数据导入

应用首次运行时，需要导入菜谱数据：

1. 在应用中进入数据导入页面
2. 点击"开始导入"按钮
3. 等待导入完成

数据来源：
- CookLikeHOC仓库中的Markdown格式菜谱
- 老乡鸡官方《菜品溯源报告》

## 数据库结构

应用使用SQLite数据库，包含以下表：

- categories: 菜谱分类
- recipes: 菜谱信息
- ingredients: 食材
- steps: 制作步骤
- favorites: 收藏
- recent_searches: 最近搜索

## 开发说明

### 添加新菜谱

1. 在CookLikeHOC仓库中添加Markdown格式菜谱
2. 使用数据导入工具导入到应用中

### 自定义主题

修改App.js中的主题配置：

```javascript
const theme = {
  colors: {
    primary: '#6200ee',
    // 其他颜色配置
  }
};
```

## 贡献指南

1. Fork项目
2. 创建功能分支
3. 提交更改
4. 推送到分支
5. 创建Pull Request

## 许可证

本项目基于APL2许可证开源。

## 致谢

- 老乡鸡官方《菜品溯源报告》
- CookLikeHOC项目 (https://github.com/Gar-b-age/CookLikeHOC)

## 联系我们

如有问题或建议，请通过以下方式联系：

- GitHub Issues: https://github.com/your-username/QiaoTime/issues