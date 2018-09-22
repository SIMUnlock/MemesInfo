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
    TypedArray memesArray;
    ArrayList<String> memesArrayInfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        memesArray = getResources().obtainTypedArray(R.array.images);
        String []memesArInfo=getResources().getStringArray(R.array.names);
        memesArrayInfo = new ArrayList<String>(Arrays.asList(memesArInfo));
        return inflater.inflate (R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity ();
        if (activity == null) return;

        RecyclerView recyclerView = activity.findViewById (R.id.rvListado);
        if (recyclerView == null) return;


        recyclerView.setLayoutManager (new GridLayoutManager(getContext (), 2,GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter (new  ListadoAdapter (getContext (), memesArrayInfo,memesArray));
    }
}

class ListadoAdapter extends RecyclerView.Adapter<ListadoViewHolder> {
    private Context context;
    private ArrayList<String> Info;
    private TypedArray imgs;
    public static int band=0;

    ListadoAdapter (Context context, ArrayList<String> info, TypedArray imgs) {
        this.context = context;
        this.Info = info;
        this.imgs=imgs;
    }

    @NonNull
    @Override
    public ListadoViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View rowView = LayoutInflater.from (context).inflate (R.layout.listado_row, viewGroup, false);
        return new ListadoViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder (@NonNull ListadoViewHolder listadoViewHolder, int i) {

        listadoViewHolder.bind (Info.get (i),imgs.getResourceId(i, -1));
    }

    @Override
    public int getItemCount () {
        return Info.size ();
    }

}

class ListadoViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;

    ListadoViewHolder (@NonNull View itemView) {
        super (itemView);
       textView = itemView.findViewById (R.id.listadoItem);
       imageView=itemView.findViewById(R.id.listadoItemImg);
    }

    public void bind (String text,int img) {


           textView.setText (text);
            imageView.setImageResource(img);

       imageView.setImageResource(img);
    }
}


