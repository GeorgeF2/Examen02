package com.example.sjaco.examen2.beans;

import android.os.Parcel;
import android.os.Parcelable;


public class ItemProduct implements Parcelable{
    private int code;
    private String title;
    private Store store;
    private String description;
    private Integer image;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ItemProduct(){
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", store=" + store +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", category=" + category +
                '}';
    }

    public Store getStore() {

        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.title);
        dest.writeParcelable(this.store, flags);
        dest.writeString(this.description);
        dest.writeValue(this.image);
        dest.writeParcelable(this.category, flags);
    }

    private ItemProduct(Parcel in) {
        this.code = in.readInt();
        this.title = in.readString();
        this.store = in.readParcelable(Store.class.getClassLoader());
        this.description = in.readString();
        this.image = (Integer) in.readValue(Integer.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };
}
