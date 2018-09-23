package com.memesinfo.memesinfoo;



import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;



public class ListadoFragment extends Fragment {
    //Se crean los arrays para el archivo arrays_1 ubicado en res/values

    //El TypedArray va a guardar los ID's de las direcciones que estàn en array name="images"
    TypedArray memesArray;

    //Este ArrayList guarda las cadenas que estàn en string-array name="names"
    ArrayList<String> memesArrayInfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Se obtienen los ID's de array name="images"
        memesArray = getResources().obtainTypedArray(R.array.images);

        //Se crea un Array de tipo String para posteriormente convertirlo en ArrayList, las cadenas que guardan son string-array name="names"
        String []memesArInfo=getResources().getStringArray(R.array.names);
        memesArrayInfo = new ArrayList<String>(Arrays.asList(memesArInfo));

        //Se crea una actividad ( se llama a onActivityCreated)
        return inflater.inflate (R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;
        //Se crea un recyclerView con el id rvListado ubicado en layout/fragment_listado
        RecyclerView recyclerView = activity.findViewById (R.id.rvListado);
        if (recyclerView == null) return;

        //En el recyclerView de layout/fragment_listado se crea un GridLayout de 2 columnas
        recyclerView.setLayoutManager (new GridLayoutManager(getContext (), 2,GridLayoutManager.VERTICAL, false));

        //Se asigna un Adapter de tipo ListadoAdapter que extiende de RecyclerView, recibe como parametros  el contexto, el arreglo de ids de las imagenes y el arreglo de la descripcion
        recyclerView.setAdapter (new  ListadoAdapter (getContext (), memesArrayInfo,memesArray));
    }
}

class ListadoAdapter extends RecyclerView.Adapter<ListadoViewHolder> {
    //Se crean variables privadas para almacenar el contexto y los arrays
    private Context context;
    private ArrayList<String> Info;
    private TypedArray imgs;

    ListadoAdapter (Context context, ArrayList<String> info, TypedArray imgs) {
        this.context = context;
        this.Info = info;
        this.imgs=imgs;
    }

    @NonNull
    @Override
    //Por cada elemento encontrado
    public ListadoViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        //Se crea una vista utilizando el xml listado_row
        View rowView = LayoutInflater.from (context).inflate (R.layout.listado_row, viewGroup, false);
        //Cuando se crea esta instancia se ejecutan todos los metodos de ListadoViewHolder
        return new ListadoViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder (@NonNull ListadoViewHolder listadoViewHolder, int i) {
        //Donde i es un contador de elementos, se obtiene i de las variables privadas de los arrays
        listadoViewHolder.bind (Info.get (i),imgs.getResourceId(i, -1));
    }

    @Override
    public int getItemCount () {
        return Info.size ();
    }

}

class ListadoViewHolder extends RecyclerView.ViewHolder {
    //Se crean 2 variables privadas para poder manipularlas
    private TextView textView;
    private ImageView imageView;

    ListadoViewHolder (@NonNull View itemView) {
        super (itemView);
        //Se asignan los id de los Textview e ImageView para reemplazarlos en el bind
       textView = itemView.findViewById (R.id.listadoItem);
       imageView=itemView.findViewById(R.id.listadoItemImg);
    }
    //bind se encarga de reemplazar los valores del xml por los recibidos en listadoViewHolder.bind
    public void bind (String text,int img) {
           textView.setText (text);
           imageView.setImageResource(img);
    }
}


