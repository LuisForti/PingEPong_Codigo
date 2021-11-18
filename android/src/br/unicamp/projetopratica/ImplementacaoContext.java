package br.unicamp.projetopratica;


import android.content.Context;

public class ImplementacaoContext implements InterfaceDoContext{

    private Context context;

    public ImplementacaoContext(Context context)
    {
        this.context = context;
    }

    @Override
    public void manipulateContext()
    {
        System.out.println(context);
    }

    @Override
    public void manipulateContextWithExtraParams(Integer example)
    {
        if(example == 1)
        {
            System.out.println(example);
        }
    }

    public Context getContext()
    {
        return context;
    }
}
