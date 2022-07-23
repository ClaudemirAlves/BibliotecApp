package com.claudemirandrade.bibliotecapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.claudemirandrade.bibliotecapp.R;
import com.claudemirandrade.bibliotecapp.conteudo.Conteudo;
import com.claudemirandrade.bibliotecapp.livro.Livro;
import com.claudemirandrade.bibliotecapp.shopping.Shopping;

public class DataBaseHelper extends SQLiteOpenHelper {
    // criando as variaveis para interagir com as tabelas
    private static final String DATABASE_NAME = "bibliotecapp";
    private static final String TABLE_LIVRO = "livro";
    private static final String TABLE_CONTEUDO = "conteudo";
    private static final String TABLE_SHOPPING = "shopping";

    // criando a tabela Livro
    private static final String CREATE_TABLE_LIVRO = "CREATE TABLE " + TABLE_LIVRO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "autor VARCHAR(100)," +
            "paginas VARCHAR(15));";

    private static final String DROP_TABLE_LIVRO = "DROP TABLE IF EXISTS " + TABLE_LIVRO;

    // criando a tabela Conteudo
    private static final String CREATE_TABLE_CONTEUDO = "CREATE TABLE " + TABLE_CONTEUDO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "sobre VARCHAR(100)," +
            "oQue VARCHAR(15));";

    private static final String DROP_TABLE_CONTEUDO = "DROP TABLE IF EXISTS " + TABLE_CONTEUDO;

    // criando a tabela Shopping
    private static final String CREATE_TABLE_SHOPPING = "CREATE TABLE " + TABLE_SHOPPING + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "sobre VARCHAR(100)," +
            "oQue VARCHAR(15));";

    private static final String DROP_TABLE_SHOPPING = "DROP TABLE IF EXISTS " + TABLE_SHOPPING;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LIVRO);
        db.execSQL(CREATE_TABLE_CONTEUDO);
        db.execSQL(CREATE_TABLE_SHOPPING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_LIVRO);
        db.execSQL(DROP_TABLE_CONTEUDO);
        db.execSQL(DROP_TABLE_SHOPPING);
        onCreate(db);
    }

    /*Inicio CRUD Livro*/
    public long createLivro (Livro l) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", l.getNome());
        cv.put("autor", l.getAutor());
        cv.put("paginas", l.getPaginas());
        long id = db.insert(TABLE_LIVRO, null, cv);
        db.close();
        return id;
    }

    public long updateLivro (Livro l) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", l.getNome());
        cv.put("autor", l.getAutor());
        cv.put("paginas", l.getPaginas());
        long id = db.update(TABLE_LIVRO, cv,
                "_id = ?", new String[]{String.valueOf(l.getId())});
        db.close();
        return id;
    }

    public long deleteLivro (Livro l) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_LIVRO,
                "_id = ?", new String[]{String.valueOf(l.getId())});
        db.close();
        return id;
    }

    public void getAllLivro (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "paginas"};
        Cursor data = db.query(TABLE_LIVRO, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarLivro, R.id.textViewNomeListarLivro,
                R.id.textViewPaginasListarLivro};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.item_list_view_livro, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Livro getByIdLivro (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "autor", "paginas"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_LIVRO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Livro l = new Livro();
        l.setId(data.getInt(0));
        l.setNome(data.getString(1));
        l.setAutor(data.getString(2));
        l.setPaginas(data.getString(3));
        data.close();
        db.close();
        return l;
    }
    /*Fim CRUD Livro*/

    /*Inicio CRUD Conteudo*/
    public long createConteudo (Conteudo c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("sobre", c.getSobre());
        cv.put("oQue", c.getoQue());
        long id = db.insert(TABLE_CONTEUDO, null, cv);
        db.close();
        return id;
    }

    public long updateConteudo (Conteudo c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("sobre", c.getSobre());
        cv.put("oQue", c.getoQue());
        long id = db.update(TABLE_CONTEUDO, cv,
                "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    public long deleteConteudo (Conteudo c) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_CONTEUDO,
                "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    public void getAllConteudo (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "oQue"};
        Cursor data = db.query(TABLE_CONTEUDO, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarConteudo, R.id.textViewNomeListarConteudo,
                R.id.textViewOQueListarConteudo};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.item_list_view_conteudo, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Conteudo getByIdConteudo (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "sobre", "oQue"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_CONTEUDO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Conteudo c = new Conteudo();
        c.setId(data.getInt(0));
        c.setNome(data.getString(1));
        c.setSobre(data.getString(2));
        c.setOoQue(data.getString(3));
        data.close();
        db.close();
        return c;
    }
    /*Fim CRUD Conteudo*/

    /*Inicio CRUD Shopping*/
    public long createShopping (Shopping s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", s.getNome());
        cv.put("sobre", s.getSobre());
        cv.put("oQue", s.getoQue());
        long id = db.insert(TABLE_SHOPPING, null, cv);
        db.close();
        return id;
    }

    public long updateShopping (Shopping s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", s.getNome());
        cv.put("sobre", s.getSobre());
        cv.put("oQue", s.getoQue());
        long id = db.update(TABLE_SHOPPING, cv,
                "_id = ?", new String[]{String.valueOf(s.getId())});
        db.close();
        return id;
    }

    public long deleteShopping (Shopping s) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_SHOPPING,
                "_id = ?", new String[]{String.valueOf(s.getId())});
        db.close();
        return id;
    }

    public void getAllShopping (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "oQue"};
        Cursor data = db.query(TABLE_SHOPPING, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarShopping, R.id.textViewNomeListarShopping,
                R.id.textViewOQueListarShopping};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.item_list_view_shopping, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Shopping getByIdShopping (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "sobre", "oQue"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_SHOPPING, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Shopping s = new Shopping();
        s.setId(data.getInt(0));
        s.setNome(data.getString(1));
        s.setSobre(data.getString(2));
        s.setOoQue(data.getString(3));
        data.close();
        db.close();
        return s;
    }
    /*Fim CRUD Shopping*/
}
