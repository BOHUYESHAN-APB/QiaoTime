package com.qiaotime.poc.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.qiaotime.poc.data.models.Recipe;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RecipeDao_Impl implements RecipeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Recipe> __insertionAdapterOfRecipe;

  public RecipeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecipe = new EntityInsertionAdapter<Recipe>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `recipes` (`id`,`title`,`slug`,`mdPath`,`coverImagePath`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Recipe entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getSlug() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSlug());
        }
        if (entity.getMdPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMdPath());
        }
        if (entity.getCoverImagePath() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCoverImagePath());
        }
      }
    };
  }

  @Override
  public Object insert(final Recipe recipe, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRecipe.insertAndReturnId(recipe);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final long id, final Continuation<? super Recipe> $completion) {
    final String _sql = "SELECT * FROM recipes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Recipe>() {
      @Override
      @Nullable
      public Recipe call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
          final int _cursorIndexOfMdPath = CursorUtil.getColumnIndexOrThrow(_cursor, "mdPath");
          final int _cursorIndexOfCoverImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImagePath");
          final Recipe _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpSlug;
            if (_cursor.isNull(_cursorIndexOfSlug)) {
              _tmpSlug = null;
            } else {
              _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            }
            final String _tmpMdPath;
            if (_cursor.isNull(_cursorIndexOfMdPath)) {
              _tmpMdPath = null;
            } else {
              _tmpMdPath = _cursor.getString(_cursorIndexOfMdPath);
            }
            final String _tmpCoverImagePath;
            if (_cursor.isNull(_cursorIndexOfCoverImagePath)) {
              _tmpCoverImagePath = null;
            } else {
              _tmpCoverImagePath = _cursor.getString(_cursorIndexOfCoverImagePath);
            }
            _result = new Recipe(_tmpId,_tmpTitle,_tmpSlug,_tmpMdPath,_tmpCoverImagePath);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object listAll(final Continuation<? super List<Recipe>> $completion) {
    final String _sql = "SELECT id, title, slug, mdPath, coverImagePath FROM recipes ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Recipe>>() {
      @Override
      @NonNull
      public List<Recipe> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfTitle = 1;
          final int _cursorIndexOfSlug = 2;
          final int _cursorIndexOfMdPath = 3;
          final int _cursorIndexOfCoverImagePath = 4;
          final List<Recipe> _result = new ArrayList<Recipe>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Recipe _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpSlug;
            if (_cursor.isNull(_cursorIndexOfSlug)) {
              _tmpSlug = null;
            } else {
              _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            }
            final String _tmpMdPath;
            if (_cursor.isNull(_cursorIndexOfMdPath)) {
              _tmpMdPath = null;
            } else {
              _tmpMdPath = _cursor.getString(_cursorIndexOfMdPath);
            }
            final String _tmpCoverImagePath;
            if (_cursor.isNull(_cursorIndexOfCoverImagePath)) {
              _tmpCoverImagePath = null;
            } else {
              _tmpCoverImagePath = _cursor.getString(_cursorIndexOfCoverImagePath);
            }
            _item = new Recipe(_tmpId,_tmpTitle,_tmpSlug,_tmpMdPath,_tmpCoverImagePath);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object searchByTitle(final String q,
      final Continuation<? super List<Recipe>> $completion) {
    final String _sql = "SELECT * FROM recipes WHERE title LIKE '%' || ? || '%' ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (q == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, q);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Recipe>>() {
      @Override
      @NonNull
      public List<Recipe> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
          final int _cursorIndexOfMdPath = CursorUtil.getColumnIndexOrThrow(_cursor, "mdPath");
          final int _cursorIndexOfCoverImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImagePath");
          final List<Recipe> _result = new ArrayList<Recipe>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Recipe _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpSlug;
            if (_cursor.isNull(_cursorIndexOfSlug)) {
              _tmpSlug = null;
            } else {
              _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            }
            final String _tmpMdPath;
            if (_cursor.isNull(_cursorIndexOfMdPath)) {
              _tmpMdPath = null;
            } else {
              _tmpMdPath = _cursor.getString(_cursorIndexOfMdPath);
            }
            final String _tmpCoverImagePath;
            if (_cursor.isNull(_cursorIndexOfCoverImagePath)) {
              _tmpCoverImagePath = null;
            } else {
              _tmpCoverImagePath = _cursor.getString(_cursorIndexOfCoverImagePath);
            }
            _item = new Recipe(_tmpId,_tmpTitle,_tmpSlug,_tmpMdPath,_tmpCoverImagePath);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object searchFts(final String q, final Continuation<? super List<Recipe>> $completion) {
    final String _sql = "SELECT recipes.* FROM recipes JOIN RecipeFts ON recipes.rowid = RecipeFts.rowid WHERE RecipeFts MATCH ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (q == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, q);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Recipe>>() {
      @Override
      @NonNull
      public List<Recipe> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
          final int _cursorIndexOfMdPath = CursorUtil.getColumnIndexOrThrow(_cursor, "mdPath");
          final int _cursorIndexOfCoverImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImagePath");
          final List<Recipe> _result = new ArrayList<Recipe>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Recipe _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpSlug;
            if (_cursor.isNull(_cursorIndexOfSlug)) {
              _tmpSlug = null;
            } else {
              _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            }
            final String _tmpMdPath;
            if (_cursor.isNull(_cursorIndexOfMdPath)) {
              _tmpMdPath = null;
            } else {
              _tmpMdPath = _cursor.getString(_cursorIndexOfMdPath);
            }
            final String _tmpCoverImagePath;
            if (_cursor.isNull(_cursorIndexOfCoverImagePath)) {
              _tmpCoverImagePath = null;
            } else {
              _tmpCoverImagePath = _cursor.getString(_cursorIndexOfCoverImagePath);
            }
            _item = new Recipe(_tmpId,_tmpTitle,_tmpSlug,_tmpMdPath,_tmpCoverImagePath);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
