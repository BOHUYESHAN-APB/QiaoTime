package com.qiaotime.poc.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.FtsTableInfo;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.qiaotime.poc.data.dao.FavoriteDao;
import com.qiaotime.poc.data.dao.FavoriteDao_Impl;
import com.qiaotime.poc.data.dao.RecipeDao;
import com.qiaotime.poc.data.dao.RecipeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile RecipeDao _recipeDao;

  private volatile FavoriteDao _favoriteDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `recipes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `slug` TEXT NOT NULL, `mdPath` TEXT NOT NULL, `coverImagePath` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `favorites` (`recipeId` INTEGER NOT NULL, `addedAt` INTEGER NOT NULL, PRIMARY KEY(`recipeId`))");
        db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `RecipeFts` USING FTS4(`title` TEXT NOT NULL, `mdPath` TEXT NOT NULL, content=`recipes`)");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_BEFORE_UPDATE BEFORE UPDATE ON `recipes` BEGIN DELETE FROM `RecipeFts` WHERE `docid`=OLD.`rowid`; END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_BEFORE_DELETE BEFORE DELETE ON `recipes` BEGIN DELETE FROM `RecipeFts` WHERE `docid`=OLD.`rowid`; END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_AFTER_UPDATE AFTER UPDATE ON `recipes` BEGIN INSERT INTO `RecipeFts`(`docid`, `title`, `mdPath`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`mdPath`); END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_AFTER_INSERT AFTER INSERT ON `recipes` BEGIN INSERT INTO `RecipeFts`(`docid`, `title`, `mdPath`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`mdPath`); END");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1302ac18e2299d66add5d3b4c8cac2bb')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `recipes`");
        db.execSQL("DROP TABLE IF EXISTS `favorites`");
        db.execSQL("DROP TABLE IF EXISTS `RecipeFts`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_BEFORE_UPDATE BEFORE UPDATE ON `recipes` BEGIN DELETE FROM `RecipeFts` WHERE `docid`=OLD.`rowid`; END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_BEFORE_DELETE BEFORE DELETE ON `recipes` BEGIN DELETE FROM `RecipeFts` WHERE `docid`=OLD.`rowid`; END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_AFTER_UPDATE AFTER UPDATE ON `recipes` BEGIN INSERT INTO `RecipeFts`(`docid`, `title`, `mdPath`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`mdPath`); END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_RecipeFts_AFTER_INSERT AFTER INSERT ON `recipes` BEGIN INSERT INTO `RecipeFts`(`docid`, `title`, `mdPath`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`mdPath`); END");
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRecipes = new HashMap<String, TableInfo.Column>(5);
        _columnsRecipes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecipes.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecipes.put("slug", new TableInfo.Column("slug", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecipes.put("mdPath", new TableInfo.Column("mdPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecipes.put("coverImagePath", new TableInfo.Column("coverImagePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRecipes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRecipes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRecipes = new TableInfo("recipes", _columnsRecipes, _foreignKeysRecipes, _indicesRecipes);
        final TableInfo _existingRecipes = TableInfo.read(db, "recipes");
        if (!_infoRecipes.equals(_existingRecipes)) {
          return new RoomOpenHelper.ValidationResult(false, "recipes(com.qiaotime.poc.data.models.Recipe).\n"
                  + " Expected:\n" + _infoRecipes + "\n"
                  + " Found:\n" + _existingRecipes);
        }
        final HashMap<String, TableInfo.Column> _columnsFavorites = new HashMap<String, TableInfo.Column>(2);
        _columnsFavorites.put("recipeId", new TableInfo.Column("recipeId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavorites.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavorites = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavorites = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavorites = new TableInfo("favorites", _columnsFavorites, _foreignKeysFavorites, _indicesFavorites);
        final TableInfo _existingFavorites = TableInfo.read(db, "favorites");
        if (!_infoFavorites.equals(_existingFavorites)) {
          return new RoomOpenHelper.ValidationResult(false, "favorites(com.qiaotime.poc.data.models.Favorite).\n"
                  + " Expected:\n" + _infoFavorites + "\n"
                  + " Found:\n" + _existingFavorites);
        }
        final HashSet<String> _columnsRecipeFts = new HashSet<String>(2);
        _columnsRecipeFts.add("title");
        _columnsRecipeFts.add("mdPath");
        final FtsTableInfo _infoRecipeFts = new FtsTableInfo("RecipeFts", _columnsRecipeFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `RecipeFts` USING FTS4(`title` TEXT NOT NULL, `mdPath` TEXT NOT NULL, content=`recipes`)");
        final FtsTableInfo _existingRecipeFts = FtsTableInfo.read(db, "RecipeFts");
        if (!_infoRecipeFts.equals(_existingRecipeFts)) {
          return new RoomOpenHelper.ValidationResult(false, "RecipeFts(com.qiaotime.poc.data.models.RecipeFts).\n"
                  + " Expected:\n" + _infoRecipeFts + "\n"
                  + " Found:\n" + _existingRecipeFts);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1302ac18e2299d66add5d3b4c8cac2bb", "2401faac0b4a848dd8f23d18d32f7032");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(1);
    _shadowTablesMap.put("RecipeFts", "recipes");
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "recipes","favorites","RecipeFts");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `recipes`");
      _db.execSQL("DELETE FROM `favorites`");
      _db.execSQL("DELETE FROM `RecipeFts`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RecipeDao.class, RecipeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FavoriteDao.class, FavoriteDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RecipeDao recipeDao() {
    if (_recipeDao != null) {
      return _recipeDao;
    } else {
      synchronized(this) {
        if(_recipeDao == null) {
          _recipeDao = new RecipeDao_Impl(this);
        }
        return _recipeDao;
      }
    }
  }

  @Override
  public FavoriteDao favoriteDao() {
    if (_favoriteDao != null) {
      return _favoriteDao;
    } else {
      synchronized(this) {
        if(_favoriteDao == null) {
          _favoriteDao = new FavoriteDao_Impl(this);
        }
        return _favoriteDao;
      }
    }
  }
}
