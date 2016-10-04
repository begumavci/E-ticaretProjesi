package comt.example.java3.eticaretprojesi;

public class Urun {

    private  String baslik,resimUrl,fiyat,aciklama,detay,productId;

public Urun(String baslik,String resimUrl,String detay, String aciklama, String fiyat,String productId){
    this.baslik = baslik;
    this.resimUrl = resimUrl;
    this.detay = detay;
    this.aciklama = aciklama;
    this.fiyat = fiyat;
    this.productId = productId;
}

    public String getProductId() {
        return productId;

    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(String resimUrl) {
        this.resimUrl = resimUrl;
    }
    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }





}

