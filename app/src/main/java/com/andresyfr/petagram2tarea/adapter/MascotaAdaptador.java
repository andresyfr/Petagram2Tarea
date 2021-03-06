package com.andresyfr.petagram2tarea.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andresyfr.petagram2tarea.DetalleMascota;
import com.andresyfr.petagram2tarea.pojo.Mascota;
import com.andresyfr.petagram2tarea.R;

import java.util.ArrayList;

/**
 * Created by andres on 16/02/17.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.ContactoViewHolder>{

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgVFotoMascotaCV;
        private ImageView imgBFavoritosCV;
        private TextView tvNombreMascoraCV;
        private TextView tvCalificacionCv;
        private ImageView imgVImagenCalificacionCv;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            imgVFotoMascotaCV         = (ImageView)itemView.findViewById(R.id.imgVFotoMascotaCV);
            imgBFavoritosCV      = (ImageView) itemView.findViewById(R.id.imgBFavoritosCV);
            tvNombreMascoraCV    = (TextView)itemView.findViewById(R.id.tvNombreMascoraCV);
            tvCalificacionCv         = (TextView)itemView.findViewById(R.id.tvCalificacionCv);
            imgVImagenCalificacionCv         = (ImageView)itemView.findViewById(R.id.imgVImagenCalificacionCv);
        }
    }

    private ArrayList<Mascota> mascotas;
    private Activity activity;

    /**
     * si es 0 Detalle mascota, si es 1 Mascotas Favoritas
     * @param contactos
     * @param activity
     */
    public MascotaAdaptador(ArrayList<Mascota> contactos, Activity activity){
        this.mascotas = contactos;
        this.activity=activity;
    }

    //Infla el layout y lo pasara al viewholder para que obtenga los view
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new ContactoViewHolder(v);
    }

    //asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(ContactoViewHolder contactoViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        contactoViewHolder.imgVFotoMascotaCV.setImageResource(mascota.getFotoMascota());
        contactoViewHolder.imgBFavoritosCV.setImageResource(mascota.isFavorito()?R.drawable.hueso_lleno:R.drawable.hueso_blanco);
        contactoViewHolder.tvNombreMascoraCV.setText(mascota.getNombreMascota());
        contactoViewHolder.tvCalificacionCv.setText(mascota.getCalificacion()+"");
        contactoViewHolder.imgVImagenCalificacionCv.setImageResource(mascota.getCalificacion()>0?R.drawable.hueso_lleno:R.drawable.hueso_blanco);

        contactoViewHolder.imgVFotoMascotaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,mascota.getNombreMascota(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(activity, DetalleMascota.class);
                    intent.putExtra(activity.getResources().getString(R.string.pImagen), mascota.getFotoMascota());
                    intent.putExtra(activity.getResources().getString(R.string.pNombre), mascota.getNombreMascota());
                    intent.putExtra(activity.getResources().getString(R.string.pCalificacion), mascota.getCalificacion());
                    intent.putExtra("Mascota", mascota);
                activity.startActivity(intent);

                //activity.finish();
            }
        });

        /*contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Diste like a "+mascota.getNombreMascota(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

}
