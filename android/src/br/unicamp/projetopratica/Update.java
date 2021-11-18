package br.unicamp.projetopratica;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Update extends SQLiteOpenHelper{
    private static final String NOME_DB = "RECORDES";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_RECORDES = "TABELA_RECORDES";
    private static final String PATH_DB = "/data/user/0/br.unicamp.projetopratica/databases/RECORDES";
    private Context mContext;
    private SQLiteDatabase db;

    public Update(Context context) {
        super(context, NOME_DB, null,VERSAO_DB);
        this.mContext = context;
        db = getWritableDatabase(); //o método indica que será escrito no db
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public boolean insertRecorde(Pontuacao p){
        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put("ID",p.getId());
            cv.put("PONTOS",p.getPontos());
            db.insert(TABELA_RECORDES,null,cv);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            db.close();
        }
    }
    public boolean updatePessoa(Pontuacao p){
        openDB();
        try {
            String where = "ID=1";
            ContentValues cv = new ContentValues();
            cv.put("PONTOS",p.getPontos());
            db.update(TABELA_RECORDES, cv, where, null);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            db.close();
        }
    }
    @SuppressLint("WrongConstant")
    private void openDB(){
        if(!db.isOpen()){
            db =
                    mContext.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}
