package co.id.fikridzakwan.example.crudemotor.Data.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.detailkategory.DetailByKategoryActivity;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.detailmotor.DetailMotorActivity;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;

public class AdapterKategoryMotor extends RecyclerView.Adapter<AdapterKategoryMotor.ViewHolder> {

    private Context context;
    private List<MotorData> motorDataList;

    public AdapterKategoryMotor(Context context, List<MotorData> motorDataList) {
        this.context = context;
        this.motorDataList = motorDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_motor_kategory, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MotorData motorData = motorDataList.get(position);
        Log.i("cek", "gambar motor" + motorData);

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context).load(motorData.getUrlMotor()).apply(options).into(holder.imgKategory);

        holder.txtNamaKategory.setText(motorData.getNamaKategori());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailByKategoryActivity.class).putExtra(Constants.KEY_EXTRA_ID_KATEGORY, motorData.getIdKategori()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return motorDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_kategory)
        ImageView imgKategory;
        @BindView(R.id.txt_nama_kategory)
        TextView txtNamaKategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
