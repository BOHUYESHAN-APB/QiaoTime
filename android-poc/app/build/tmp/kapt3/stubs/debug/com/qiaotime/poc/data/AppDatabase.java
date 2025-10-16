package com.qiaotime.poc.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/qiaotime/poc/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "favoriteDao", "Lcom/qiaotime/poc/data/dao/FavoriteDao;", "recipeDao", "Lcom/qiaotime/poc/data/dao/RecipeDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.qiaotime.poc.data.models.Recipe.class, com.qiaotime.poc.data.models.Favorite.class, com.qiaotime.poc.data.models.RecipeFts.class}, version = 2)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String DB_NAME = "qiaotime_poc.db";
    @org.jetbrains.annotations.NotNull
    public static final com.qiaotime.poc.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.qiaotime.poc.data.dao.RecipeDao recipeDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.qiaotime.poc.data.dao.FavoriteDao favoriteDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/qiaotime/poc/data/AppDatabase$Companion;", "", "()V", "DB_NAME", "", "getInstance", "Lcom/qiaotime/poc/data/AppDatabase;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.qiaotime.poc.data.AppDatabase getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}