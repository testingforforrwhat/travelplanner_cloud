package com.test.travelplanner;


import com.test.travelplanner.model.entity.DestinationEntity;
import com.test.travelplanner.model.entity.Product;
import com.test.travelplanner.model.entity.trip.*;
import com.test.travelplanner.model.entity.user.*;
import com.test.travelplanner.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


/**
 * add init data
 *
 * 通常推荐ApplicationRunner。原因：参数解析能力更强，更易扩展功能。
 *
 * ApplicationRunner CommandLineRunner
 *
 * 你可以安全地读写数据库，插入初始数据、生成表、做基础校验等。
 *
 */
@Component
public class DevRunner implements ApplicationRunner {


    static private final Logger logger = LoggerFactory.getLogger(DevRunner.class);

    private final DestinationRepository destinationRepository;
    private final ProductRepository productRepository;
    private final TripRepository tripRepository;
    private final TripOverviewRepository tripOverviewRepository;
    private final TripHighlightRepository tripHighlightRepository;
    private final TripActivityRepository tripActivityRepository;
    private final TripItineraryRepository tripItineraryRepository;
    private final MenuRepository menuRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionMenuRepository permissionMenuRepository;


    public DevRunner(

            DestinationRepository destinationRepository, ProductRepository productRepository,

            TripRepository tripRepository, TripOverviewRepository tripOverviewRepository, TripHighlightRepository tripHighlightRepository, TripActivityRepository tripActivityRepository, TripItineraryRepository tripItineraryRepository, MenuRepository menuRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            RolePermissionRepository rolePermissionRepository,
            PermissionMenuRepository permissionMenuRepository) {

        this.destinationRepository = destinationRepository;
        this.productRepository = productRepository;
        this.tripRepository = tripRepository;
        this.tripOverviewRepository = tripOverviewRepository;
        this.tripHighlightRepository = tripHighlightRepository;
        this.tripActivityRepository = tripActivityRepository;
        this.tripItineraryRepository = tripItineraryRepository;
        this.menuRepository = menuRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionMenuRepository = permissionMenuRepository;
    }


    @Override
    @Transactional // 建议在事务中执行，确保数据一致性
    public void run(ApplicationArguments args) {

        logger.info("开始添加开发环境测试数据...");

        generateSampleData();

        List<DestinationEntity> destinationEntity = destinationRepository.findAll();
        logger.info("Found {} destinations: {}", destinationEntity.size(), destinationEntity);

        List<Product> products = productRepository.findAll();
        logger.info("Found {} products: {}", products.size(), products);

    }


    private void generateSampleData() {

        destinationRepository.saveAll(List.of(
              new DestinationEntity("洛杉矶","洛杉矶","阳光明媚，电影之都的魅力","require('@/assets/images/la/santa-monica.png')",2.0),
              new DestinationEntity("悉尼","洛杉矶","悉尼歌剧院，海港大桥的壮丽景色","require('@/assets/images/sydney.jpg')",2.0),
              new DestinationEntity("北京","洛杉矶","历史悠久，长城与故宫的辉煌","require('@/assets/images/beijing.jpeg')",2.0),
              new DestinationEntity("test","test","test","require('@/assets/images/beijing.jpeg')",2.0)
        ));

        Product product = new Product();
        product.setName("测试商品");
        product.setPrice(100.0);
        product.setStock(10);
        productRepository.save( product );

        Trip trip = new Trip();
        trip.setTitle("洛杉矶深度探索之旅");
        trip.setBannerImage("require('@/assets/logo.png')");
        trip.setDuration(7);
        trip.setLocation("美国·洛杉矶");
        trip.setPrice(BigDecimal.valueOf(0));
        tripRepository.save( trip );

        TripOverview tripOverview = new TripOverview();
        tripOverview.setTrip(trip);
        tripOverview.setIcon("fas fa-calendar");
        tripOverview.setTitle("最佳旅行时间");
        tripOverview.setContent("3月-5月，9月-11月");
        tripOverviewRepository.save(tripOverview);

        TripOverview tripOverview2 = new TripOverview();
        tripOverview2.setTrip(trip);
        tripOverview2.setIcon("fas fa-user-friends");
        tripOverview2.setTitle("适合人群");
        tripOverview2.setContent("情侣、家庭、好友");
        tripOverviewRepository.save(tripOverview2);

        TripHighlight tripHighlight = new TripHighlight();
        tripHighlight.setTrip(trip);
        tripHighlight.setTitle("好莱坞环球影城");
        tripHighlight.setDescription("体验世界顶级主题公园，感受电影的魅力");
        tripHighlightRepository.save( tripHighlight );

        TripItinerary tripItinerary = new TripItinerary();
        tripItinerary.setTrip(trip);
        tripItinerary.setTitle("抵达洛杉矶");
        tripItineraryRepository.save( tripItinerary );

        TripActivity tripActivity = new TripActivity();
        tripActivity.setItinerary(tripItinerary);
        tripActivity.setTime("14:00");
        tripActivity.setTitle("抵达机场");
        tripActivity.setDescription("LAX机场接机，入住酒店");
        tripActivityRepository.save( tripActivity );

        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        roleRepository.save( role );

        Role role2 = new Role();
        role2.setRoleName("ROLE_USER");
        roleRepository.save( role2 );

        // 创建权限
        Permission sysSettingsPerm = new Permission();
        sysSettingsPerm.setPermissionName("系统设置");
        // sysSettingsPerm.setPid(null); // 如果有父权限ID，则设置
        sysSettingsPerm.setPid(1);
        sysSettingsPerm = permissionRepository.save(sysSettingsPerm);

        Permission menuManagePerm = new Permission();
        menuManagePerm.setPermissionName("菜单管理");
        // menuManagePerm.setPid(sysSettingsPerm.getPermissionId()); // 示例：设为系统设置的子权限
        menuManagePerm.setPid(2);
        menuManagePerm = permissionRepository.save(menuManagePerm);

        Permission opManagePerm = new Permission();
        opManagePerm.setPermissionName("操作管理");
        // opManagePerm.setPid(sysSettingsPerm.getPermissionId()); // 示例：设为系统设置的子权限
        opManagePerm.setPid(3);
        opManagePerm = permissionRepository.save(opManagePerm);

        // 分配角色权限
        RolePermission adminRoleSysPerm = new RolePermission();
        adminRoleSysPerm.setRole(role);
        adminRoleSysPerm.setPermission(sysSettingsPerm);
        rolePermissionRepository.save(adminRoleSysPerm);

        RolePermission adminRoleMenuPerm = new RolePermission();
        adminRoleMenuPerm.setRole(role2);
        adminRoleMenuPerm.setPermission(menuManagePerm);
        rolePermissionRepository.save(adminRoleMenuPerm);

        RolePermission adminRoleOpPerm = new RolePermission();
        adminRoleOpPerm.setRole(role);
        adminRoleOpPerm.setPermission(opManagePerm);
        rolePermissionRepository.save(adminRoleOpPerm);

        RolePermission normalUserMenuPerm = new RolePermission();
        normalUserMenuPerm.setRole(role);
        normalUserMenuPerm.setPermission(menuManagePerm);
        rolePermissionRepository.save(normalUserMenuPerm);

        Menu menu = new Menu();
        menu.setMenuName("StockDashboard");
        menu.setMenuUrl("/stockDashboard");
        menu.setPid(1);
        menuRepository.save( menu );

        Menu menu2 = new Menu();
        menu2.setMenuName("BlogComponent");
        menu2.setMenuUrl("/blog");
        menu2.setPid(2);
        menuRepository.save( menu2 );

        // 分配权限给菜单
        PermissionMenu permMenuLink1 = new PermissionMenu();
        permMenuLink1.setPermissionId(menuManagePerm.getPermissionId());
        permMenuLink1.setMenuId(menu.getMenuId());
        permissionMenuRepository.save(permMenuLink1);

        PermissionMenu permMenuLink2 = new PermissionMenu();
        permMenuLink2.setPermissionId(menuManagePerm.getPermissionId()); // 假设菜单管理权限也能看用户管理菜单
        permMenuLink2.setMenuId(menu2.getMenuId());
        permissionMenuRepository.save(permMenuLink2);
    }
}
