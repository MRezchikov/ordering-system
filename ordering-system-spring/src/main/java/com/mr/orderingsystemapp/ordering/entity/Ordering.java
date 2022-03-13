package com.mr.orderingsystemapp.ordering.entity;

import com.mr.orderingsystemapp.orderingitems.entity.OrderingItems;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Ordering {

    public Ordering() {
    }

    public Ordering(Long id,
                    String username,
                    Boolean done) {
        this.id = id;
        this.username = username;
        this.done = done;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "done")
    private Boolean done;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ordering_id")
    private List<OrderingItems> orderingItems;
}
