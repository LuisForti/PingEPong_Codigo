package br.unicamp.projetopratica;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Create extends SQLiteOpenHelper {
    private static final String NOME_DB = "RECORDES";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_RECORDES = "TABELA_RECORDES";
    private static final String PATH_DB = "/data/user/0/br.unicamp.projetopratica/databases/RECORDES";
    private Context mContext;
    private SQLiteDatabase db; //instância de banco de dados
    //O null indica que usaremos um cursor padrão
    public Create(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean createTable(){
        openDB();
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABELA_RECORDES
                + "("
                + "ID INTEGER, "
                + "PONTOS INTEGER)";

        try{
            db.execSQL(createTable);
            ContentValues cv = new ContentValues();
            cv.put("ID", 1);
            cv.put("PONTOS", 0);
            db.insert(TABELA_RECORDES,null,cv);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            db.close(); //fecha o banco independente de ter conseguido executar ou não
        }
    }
    @SuppressLint("WrongConstant")
    private void openDB(){
        if(!db.isOpen()){
            db = mContext.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}

