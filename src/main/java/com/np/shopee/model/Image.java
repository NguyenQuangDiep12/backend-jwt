package com.np.shopee.model;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    private Blob image;
    private String downloadUrl;

    // @JsonIgnore Công dụng: Dùng để bỏ qua một thuộc tính khi Jackson tuần tự hóa
    // (serialize) hoặc giải tuần tự hóa (deserialize) đối tượng.

    // Công dụng: Cặp @JsonManagedReference và @JsonBackReference được sử dụng để xử
    // lý các mối quan hệ hai chiều (bi-directional relationships) giữa các đối
    // tượng trong quá trình tuần tự hóa JSON mà không bị vòng lặp vô hạn.

    // @JsonManagedReference: Được đặt ở phía "cha" (parent), giúp Jackson biết đây
    // là nơi bắt đầu chuỗi tham chiếu.
    // @JsonBackReference: Được đặt ở phía "con" (child), giúp Jackson biết không
    // cần tuần tự hóa thuộc tính ngược lại về "cha", để tránh vòng lặp.

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
