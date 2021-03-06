package com.haiyan.deflower.service.impl;

import com.haiyan.deflower.dao.*;
import com.haiyan.deflower.dto.response.AgeAnalysisVo;
import com.haiyan.deflower.dto.response.FlowerRankingVo;
import com.haiyan.deflower.dto.response.RegionalDistributionVo;
import com.haiyan.deflower.dto.response.StatisticsTopVo;
import com.haiyan.deflower.pojo.Flower;
import com.haiyan.deflower.pojo.Order;
import com.haiyan.deflower.pojo.OrderDetail;
import com.haiyan.deflower.pojo.User;
import com.haiyan.deflower.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maple
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderDao orderDao;
    private final FlowerDao flowerDao;
    private final VideoTeachingDao videoTeachingDao;
    private final UserDao userDao;
    private final OrderDetailDao orderDetailDao;

    public StatisticsServiceImpl(OrderDao orderDao, FlowerDao flowerDao, VideoTeachingDao videoTeachingDao, UserDao userDao, OrderDetailDao orderDetailDao) {
        this.orderDao = orderDao;
        this.flowerDao = flowerDao;
        this.videoTeachingDao = videoTeachingDao;
        this.userDao = userDao;
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public StatisticsTopVo getStatisticsTopVo() {
        StatisticsTopVo statisticsTopVo = new StatisticsTopVo();
        List<Order> orders = orderDao.lambdaQuery().select(Order::getActualPay).list();
        statisticsTopVo.setSaleroom(orders.stream().mapToDouble(Order::getActualPay).sum());
        statisticsTopVo.setOrderQuantity(orders.size());
        statisticsTopVo.setNumberGoods(flowerDao.lambdaQuery().count());
        statisticsTopVo.setNumberVideo(videoTeachingDao.lambdaQuery().count());
        statisticsTopVo.setTotalNumberRegistrants(userDao.lambdaQuery().count());
        Integer menCount = userDao.lambdaQuery().eq(User::getSex, "???").count();
        Integer womenCount = userDao.lambdaQuery().eq(User::getSex, "???").count();
        statisticsTopVo.setMenThan(Chufa(menCount,statisticsTopVo.getTotalNumberRegistrants())*100);
        statisticsTopVo.setWomenThan(Chufa(womenCount,statisticsTopVo.getTotalNumberRegistrants())*100);
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        statisticsTopVo.setNewRegistrations(userDao.lambdaQuery().between(User::getCreateTime,today_start,today_end).count());
        return statisticsTopVo;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double Chufa(int a,int b) {
        double jieguo = new BigDecimal((float)a/b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return jieguo;
    }

    @Override
    public List<AgeAnalysisVo> getAgeAnalysisVo() {
        List<AgeAnalysisVo> ageAnalysisVos = new ArrayList<>();
        AgeAnalysisVo ageAnalysisVo = new AgeAnalysisVo();
        ageAnalysisVo.setName("15~25???");
        ageAnalysisVo.setValue(userDao.lambdaQuery().between(User::getAge,15,25).count());
        if (ageAnalysisVo.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo);
        }
        AgeAnalysisVo ageAnalysisVo1 = new AgeAnalysisVo();
        ageAnalysisVo1.setName("25~35???");
        ageAnalysisVo1.setValue(userDao.lambdaQuery().between(User::getAge,25,35).count());
        if (ageAnalysisVo1.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo1);
        }
        AgeAnalysisVo ageAnalysisVo2 = new AgeAnalysisVo();
        ageAnalysisVo2.setName("35~45???");
        ageAnalysisVo2.setValue(userDao.lambdaQuery().between(User::getAge,35,45).count());
        if (ageAnalysisVo2.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo2);
        }
        AgeAnalysisVo ageAnalysisVo3 = new AgeAnalysisVo();
        ageAnalysisVo3.setName("45~55???");
        ageAnalysisVo3.setValue(userDao.lambdaQuery().between(User::getAge,45,55).count());
        if (ageAnalysisVo3.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo3);
        }
        AgeAnalysisVo ageAnalysisVo4 = new AgeAnalysisVo();
        ageAnalysisVo4.setName("55~65???");
        ageAnalysisVo4.setValue(userDao.lambdaQuery().between(User::getAge,55,65).count());
        if (ageAnalysisVo4.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo4);
        }
        AgeAnalysisVo ageAnalysisVo5 = new AgeAnalysisVo();
        ageAnalysisVo5.setName("65~75???");
        ageAnalysisVo5.setValue(userDao.lambdaQuery().between(User::getAge,65,75).count());
        if (ageAnalysisVo5.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo5);
        }
        AgeAnalysisVo ageAnalysisVo6 = new AgeAnalysisVo();
        ageAnalysisVo6.setName("75~85???");
        ageAnalysisVo6.setValue(userDao.lambdaQuery().between(User::getAge,75,85).count());
        if (ageAnalysisVo6.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo6);
        }
        AgeAnalysisVo ageAnalysisVo7 = new AgeAnalysisVo();
        ageAnalysisVo7.setName("85?????????");
        ageAnalysisVo7.setValue(userDao.lambdaQuery().between(User::getAge,85,100000).count());
        if (ageAnalysisVo7.getValue()>0) {
            ageAnalysisVos.add(ageAnalysisVo7);
        }
        return ageAnalysisVos;
    }

    @Override
    public RegionalDistributionVo getRegionalDistributionVo() {
        RegionalDistributionVo regionalDistributionVo = new RegionalDistributionVo();
        List<String> addrs = new ArrayList<>();
        List<List<Object>> source = new ArrayList<>();
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????????????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????????????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("??????????????????");
        addrs.add("?????????????????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("????????????????????????");
        addrs.add("?????????");
        addrs.add("?????????");
        addrs.add("?????????????????????");

        addrs.forEach(addr -> {
            List<Object> list = new ArrayList<>();
            list.add(addr);
            Integer count = orderDao.lambdaQuery().eq(Order::getReceiverState, addr).count();
            list.add(count);
            if (count>0) {
                source.add(list);
            }
        });
        regionalDistributionVo.setSource(source);
        return regionalDistributionVo;
    }

    @Override
    public FlowerRankingVo getFlowerRankingVo() {
        FlowerRankingVo flowerRankingVo = new FlowerRankingVo();
        List<OrderDetail> list = orderDetailDao.lambdaQuery().select(OrderDetail::getSkuId, OrderDetail::getNum).list();
        Map<Long, List<OrderDetail>> dateListMap = list.stream()
                .collect(Collectors.groupingBy(OrderDetail::getSkuId));
        // ??????map,???????????????????????????
        HashMap<String, Integer> resMap = new HashMap<>(128);
        for (Map.Entry<Long, List<OrderDetail>> detailEntry:dateListMap.entrySet()){
            Flower flower = flowerDao.getById(detailEntry.getKey());
            AtomicReference<Integer> sumNum = new AtomicReference<>(0);
            detailEntry.getValue().forEach(orderDetail -> sumNum.updateAndGet(v -> v + orderDetail.getNum()));
            if (flower!=null) {
                resMap.put(flower.getTitle(),sumNum.get());
            }
        }
        // ??????
        Stream<Map.Entry<String, Integer>> st = resMap.entrySet().stream();
        Map<String, Integer> result = new LinkedHashMap<>(32);
        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        List<List<Object>> source = new ArrayList<>();
        for (Map.Entry<String, Integer> integerEntry:result.entrySet()){
            List<Object> objects = new ArrayList<>();
            objects.add(integerEntry.getKey());
            objects.add(integerEntry.getValue());
            source.add(objects);
        }
        flowerRankingVo.setSource(source);
        return flowerRankingVo;
    }
}
