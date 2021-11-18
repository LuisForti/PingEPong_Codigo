package br.unicamp.projetopratica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.unicamp.projetopratica.Pontuacao;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class Read extends SQLiteOpenHelper{
    private static final String NOME_DB = "RECORDES";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_RECORDES = "TABELA_RECORDES";
    private static final String PATH_DB = "/data/user/0/br.unicamp.projetopratica/databases/RECORDES";
    private Context mContext;
    private SQLiteDatabase db;

    public Read(Context context) {
        super(context, NOME_DB, null,VERSAO_DB);
        this.mContext = context;
        db = getReadableDatabase(); //o método indica que será lido do db
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }

    public ArrayList<Pontuacao> getPontuacao() {
        openDB();
        ArrayList<Pontuacao> pArray = new ArrayList<>();
        String getPontuacao = "select * from " + TABELA_RECORDES;
        try{
            Cursor c = db.rawQuery(getPontuacao,null);
            if(c.moveToFirst()){
                do{
                    Pontuacao p = new Pontuacao();
                    p.setId(c.getInt(0));
                    p.setPontos(c.getInt(1));
                    pArray.add(p);
                } while(c.moveToNext());
                c.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            db.close();
        }
        return pArray;
    }

    @SuppressLint("WrongConstant")
    private void openDB(){
        if(!db.isOpen()){
            db =
                    mContext.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}

