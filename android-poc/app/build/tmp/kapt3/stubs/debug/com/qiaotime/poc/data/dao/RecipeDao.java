package com.qiaotime.poc.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/qiaotime/poc/data/dao/RecipeDao;", "", "getById", "Lcom/qiaotime/poc/data/models/Recipe;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "recipe", "(Lcom/qiaotime/poc/data/models/Recipe;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchByTitle", "q", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchFts", "app_debug"})
@androidx.room.Dao
public abstract interface RecipeDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.qiaotime.poc.data.models.Recipe recipe, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM recipes WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.qiaotime.poc.data.models.Recipe> $completion);
    
    @androidx.room.Query(value = "SELECT id, title, slug, mdPath, coverImagePath FROM recipes ORDER BY id DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object listAll(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.qiaotime.poc.data.models.Recipe>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM recipes WHERE title LIKE \'%\' || :q || \'%\' ORDER BY id DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchByTitle(@org.jetbrains.annotations.NotNull
    java.lang.String q, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.qiaotime.poc.data.models.Recipe>> $completion);
    
    @androidx.room.Query(value = "SELECT recipes.* FROM recipes JOIN RecipeFts ON recipes.rowid = RecipeFts.rowid WHERE RecipeFts MATCH :q")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchFts(@org.jetbrains.annotations.NotNull
    java.lang.String q, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.qiaotime.poc.data.models.Recipe>> $completion);
}