package com.qiaotime.poc.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/qiaotime/poc/data/dao/FavoriteDao;", "", "add", "", "fav", "Lcom/qiaotime/poc/data/models/Favorite;", "(Lcom/qiaotime/poc/data/models/Favorite;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "remove", "recipeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface FavoriteDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object add(@org.jetbrains.annotations.NotNull
    com.qiaotime.poc.data.models.Favorite fav, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM favorites ORDER BY addedAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object listAll(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.qiaotime.poc.data.models.Favorite>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM favorites WHERE recipeId = :recipeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object remove(long recipeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}