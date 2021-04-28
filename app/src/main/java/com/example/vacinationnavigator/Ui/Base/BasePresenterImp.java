package com.example.vacinationnavigator.Ui.Base;

public class BasePresenterImp <V extends BaseView> implements BasePresenter<V> {
    private V view ;

    @Override
    public void onAttach(V view){
        this.view = view;
    }

    @Override
    public void onDetach(){
        this.view = null;
    }

    public boolean isViewAttached(){
        return  this.view != null;
    }

    public  void checkViewAttached(){
        if(!isViewAttached()) throw  new VTPContractNotAttachedException();
    }

    public V  getView(){
        return view;

    }

    public  static  class VTPContractNotAttachedException extends  RuntimeException{
        public VTPContractNotAttachedException(){
            super("Please call Presenter.onAttach(View) before" +
                    " requesting data to the Presenter");
        }
    }
}
