package com.example.tugas3_crypto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.ViewHolder> {
    private List<CryptoModel> cryptoList;

    public CryptoAdapter(List<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Memanggil layout item_crypto.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_crypto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Mengisi data ke textview
        CryptoModel crypto = cryptoList.get(position);
        holder.tvRank.setText(crypto.getRank());
        holder.tvName.setText(crypto.getName());
        holder.tvSymbol.setText(crypto.getSymbol());
        holder.tvPrice.setText(crypto.getPriceUsd());
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRank, tvName, tvSymbol, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Menghubungkan variabel dengan ID di XML
            tvRank = itemView.findViewById(R.id.tvRank);
            tvName = itemView.findViewById(R.id.tvName);
            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
