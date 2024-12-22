package com.kaiyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.entity.Building;
import com.kaiyu.domain.vo.BuildingDateVo;
import com.kaiyu.domain.vo.BuildingRest;
import com.kaiyu.domain.vo.BuildingInfoRest;
import com.kaiyu.domain.vo.FloorRest;
import com.kaiyu.domain.vo.RoomRest;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

public interface BuildingMapper extends BaseMapper<BuildingInfo> {

  @Select("SELECT * FROM building_info WHERE id = #{id}")
  BuildingInfo selectBuildingInfoById(Long id);
  
  @Select("SELECT * FROM building_info WHERE building_num = #{buildingNum} AND floor = #{floor} AND room_num = #{roomNum}")
  BuildingInfo selectBuildingInfoByBuildingFloorRoom(@Param("buildingNum") String buildingNum, @Param("floor") String floor, @Param("roomNum") String roomNum);
  
  @Select("SELECT * FROM building_info ")
  List<BuildingInfo> selectBuildingInfoAll();
  
  @Insert("INSERT INTO building_info (building_num, floor, room_num, capacity, capacity_num, room_type, room_standard, remark) VALUES (#{buildingNum}, #{floor}, #{roomNum}, #{capacity}, #{capacityNum}, #{roomType}, #{roomStandard}, #{remark})")
  void insertBuildingInfo(BuildingInfo paramBuildingInfo);
  
  
  @Update("UPDATE building_info SET building_num = #{buildingNum}, floor = #{floor}, room_num = #{roomNum}, capacity = #{capacity}, capacity_num = #{capacityNum}, room_type = #{roomType}, room_standard = #{roomStandard}, remark = #{remark} WHERE id = #{id}")
  void updateBuildingInfo(BuildingInfo paramBuildingInfo);

  @Update("UPDATE building_info SET building_num = #{buildingNum}, floor = #{floor}, room_num = #{roomNum}, capacity = #{capacity}, capacity_num = #{capacityNum}, room_type = #{roomType}, room_standard = #{roomStandard}, remark = #{remark} WHERE building_num = #{buildingNum} AND room_num = #{roomNum}")
  int updateBuildingBatch(BuildingInfo paramBuildingInfo);
  
  @Delete("DELETE FROM building_info WHERE id = #{id}")
  void deleteBuildingInfoById(Long id);
  
  
  List<BuildingDateVo> countData(BuildingInfo paramBuildingInfo);
  
  List<String> deptList();
  
  List<String> selectBuildingInfoNumList();
  
  List<String> selectRoomNumList(@Param("BuildingInfoNum") String paramString1, @Param("floor") String paramString2);
  
  BuildingDateVo selectRoomRest(@Param("BuildingInfoNum") String paramString1, @Param("floor") String paramString2, @Param("roomNum") String paramString3);
  
  @Select("SELECT DISTINCT building_num FROM building_info")
  List<String> getBuildingInfoName();
  
  @Select("SELECT * " + 
        "FROM building_info b1 " + 
        "WHERE b1.room_num IN ( " + 
        "    SELECT DISTINCT room_num " + 
        "    FROM building_info " + 
        ") " + 
        "ORDER BY b1.room_num ASC")
  List<BuildingInfo> getRoomNumList();

  @Select("SELECT LEFT(building_num, 2) AS life_area, SUM(capacity) AS total_capacity, SUM(capacity_num) AS total_occupied, SUM(capacity - capacity_num) AS total_vacancy, ROUND(SUM(capacity_num) / SUM(capacity) * 100, 2) AS occupancy_rate FROM building_info WHERE building_num LIKE #{buildingNum} GROUP BY LEFT(building_num, 2); ")
  BuildingRest getBuildingRest(@Param("buildingNum") String BuildingNum);

  
  @Select("SELECT building_num AS building, SUM(capacity) AS total_capacity, SUM(capacity_num) AS total_occupied, SUM(capacity - capacity_num) AS total_vacancy, ROUND(SUM(capacity_num) / SUM(capacity) * 100, 2) AS occupancy_rate FROM building_info WHERE building_num = #{buildingNum} GROUP BY building_num;")
  BuildingInfoRest getBuildingInfoRest(@Param("buildingNum") String BuildingNum);


  @Select("SELECT building_num AS building, floor AS floor_info, SUM(capacity) AS total_capacity, SUM(capacity_num) AS total_occupied, SUM(capacity - capacity_num) AS total_vacancy, ROUND(SUM(capacity_num) / SUM(capacity) * 100, 2) AS occupancy_rate FROM building_info WHERE building_num = #{buildingNum} AND floor = #{floor} GROUP BY building_num, floor;")
  FloorRest getFloorRest(@Param("buildingNum") String BuildingNum, @Param("floor") String floor);


  @Select("SELECT building_num AS building, floor AS floor_info, room_num AS room, capacity AS total_capacity, capacity_num AS total_occupied, (capacity - capacity_num) AS total_vacancy, ROUND(capacity_num / capacity * 100, 2) AS occupancy_rate FROM building_info WHERE building_num = #{buildingNum} AND floor = #{floor} AND room_num = #{roomNum};")
  RoomRest getRoomRest(@Param("buildingNum") String BuildingNum, @Param("floor") String floor, @Param("roomNum") String roomNum);


  int batchUpdateBuildingInfoName(@Param("buildingInfoName") String paramString1, @Param("newName") String paramString2);
}

