// Trip.java - 主实体类
package com.test.travelplanner.model.entity.trip;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 实体关系结构：
 * 主实体 Trip 与子实体采用一对多关系
 * 每个子实体都有对应的数据库表
 * 使用 CascadeType.ALL 确保级联操作，实现自动保存/删除子实体
 * 
 * 
 * Hibernate 注解使用：
 * @Entity：标记 JPA 实体类
 * @Table：指定对应的数据库表名
 * @Column：自定义列名或属性
 * @OneToMany：一对多关系映射
 * @ManyToOne：多对一关系映射
 * 
 * 
 * 优化设计：
 * 使用 FetchType.LAZY 延迟加载提高性能
 * 使用 orphanRemoval=true 自动清理孤立数据
 * 添加辅助方法方便实体对象关系维护
 * 数据库添加索引提高查询性能
 * 
 */
@Entity
@Table(name = "trips")
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "banner_image")
    private String bannerImage;
    
    private Integer duration;
    
    private String location;
    
    private BigDecimal price;

    /**
     *
     * 双向关系建议用 @JsonManagedReference / @JsonBackReference；
     * 或给子表 ManyToOne 关联加 @JsonIgnore；
     * 或 DTO 分离前后端对象。
     *
     * 推荐采用 DTO 模式，：
     * 解耦表现层和持久层：实体类专注于数据库映射，DTO专注于API表现
     * 更灵活的API设计：可以根据不同API需求设计不同的DTO
     * 更好的安全控制：避免敏感字段意外暴露
     * 性能优化：只传输需要的数据
     *
     */
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  //   @JsonManagedReference + @JsonBackReference  避免循环引用; 保持数据库映射的双向关联，但序列化（JSON输出时） 避免循环引用
    private List<TripOverview> overview = new ArrayList<>();
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TripHighlight> highlights = new ArrayList<>();
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @OrderBy("dayNumber ASC")
    private List<TripItinerary> itinerary = new ArrayList<>();
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TripPriceInclude> priceIncludes = new ArrayList<>();
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TripPriceExclude> priceExcludes = new ArrayList<>();
    
    // 辅助方法，简化添加关联实体
    public void addOverview(TripOverview overview) {
        this.overview.add(overview);
        overview.setTrip(this);
    }
    
    public void addHighlight(TripHighlight highlight) {
        this.highlights.add(highlight);
        highlight.setTrip(this);
    }
    
    public void addItinerary(TripItinerary itinerary) {
        this.itinerary.add(itinerary);
        itinerary.setTrip(this);
    }
    
    public void addPriceInclude(TripPriceInclude priceInclude) {
        this.priceIncludes.add(priceInclude);
        priceInclude.setTrip(this);
    }
    
    public void addPriceExclude(TripPriceExclude priceExclude) {
        this.priceExcludes.add(priceExclude);
        priceExclude.setTrip(this);
    }
}