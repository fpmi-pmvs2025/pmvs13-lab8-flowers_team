package bsu.pi_13.flowers_team.data.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bsu.pi_13.flowers_team.data.db.DBHelper;
import bsu.pi_13.flowers_team.data.model.Flower;

public class FlowerRepository {
    private final SQLiteDatabase database;

    public FlowerRepository(SQLiteDatabase database) {
        this.database = database;
    }

    public List<Flower> getAllFlowers() {
        List<Flower> flowers = new ArrayList<>();

        Cursor cursor = database.query(
                DBHelper.TABLE_FLOWERS,
                null,
                null,
                null,
                null,
                null,
                "name ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                Flower flower = new Flower();
                flower.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                flower.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                flower.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow("price")));
                flower.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow("image_url")));
                flower.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                flowers.add(flower);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return flowers;
    }
}
