package com.kaiyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.domain.dto.ExchangeStaffRoomDTO;

public interface StaffInfoMapper extends BaseMapper<StaffInfo> {
//     @Select("SELECT * FROM building_info ")
//   List<StaffInfo> selectStaffInfoAll();
  
//   @Insert("INSERT INTO building_info (building_num, floor, room_num, capacity, capacity_num, room_type, room_standard, remark) VALUES (#{buildingNum}, #{floor}, #{roomNum}, #{capacity}, #{capacityNum}, #{roomType}, #{roomStandard}, #{remark})")
//   void insertStaffInfo(StaffInfo paramStaffInfo);
  
  
  @Update("UPDATE staff_info SET building_num = #{buildingNum}, floor = #{floor}, room_num = #{roomNum},room_standard = #{roomStandard}, staff_num = #{staffNum}, staff_name = #{staffName}, sex = #{sex}, contry = #{contry}, company = #{company}, dept = #{dept}, post = #{post}, phone = #{phone}, visa_type = #{visaType}, passport_no = #{passportNo}, bed_num = #{bedNum}, key_flag = #{keyFlag}, checkin_date = #{checkinDate}, deposit = #{deposit}, remark = #{remark}, update_by = #{updateBy}, update_time = #{updateTime} WHERE staff_num = #{staffNum} OR passport_no = #{passportNo} OR (building_num = #{buildingNum} AND room_num = #{roomNum} AND staff_name = #{staffName}) OR phone = #{phone} ;")
  int updateStaffInfo(StaffInfo paramStaffInfo);

//   @Update("UPDATE building_info SET building_num = #{buildingNum}, floor = #{floor}, room_num = #{roomNum}, capacity = #{capacity}, capacity_num = #{capacityNum}, room_type = #{roomType}, room_standard = #{roomStandard}, remark = #{remark} WHERE building_num = #{buildingNum} AND room_num = #{roomNum}")
//   void updateStaffBatch(StaffInfo paramStaffInfo);
  
//   @Delete("DELETE FROM building_info WHERE id = #{id}")
//   void deleteStaffInfoById(Long paramLong);
   @Select("SELECT room_standard FROM staff_info WHERE building_num = #{selectedBuilding} AND floor = #{selectedFloor} AND room_num = #{selectedRoom} LIMIT 1 ")
   String selectedRoomInfo(ExchangeStaffRoomDTO dto);

   @Update("UPDATE staff_info SET building_num = #{selectedBuilding}, floor = #{selectedFloor}, room_num = #{selectedRoom}, room_standard = #{selectedRoomStandard}, update_by = #{updateBy}, update_time = #{updateTime} WHERE building_num = #{buildingNum} AND floor = #{floor} AND room_num = #{roomNum} AND staff_name = #{staffName}")
   void updateRoom(ExchangeStaffRoomDTO dto);

   @Select("SELECT room_standard, bed_num FROM staff_info WHERE building_num = #{selectedBuilding} AND floor = #{selectedFloor} AND room_num = #{selectedRoom} AND staff_name = #{selectedStaff}")
   StaffInfo selectedInfo(ExchangeStaffRoomDTO dto);

   @Select("SELECT room_standard, bed_num FROM staff_info WHERE building_num = #{buildingNum} AND floor = #{floor} AND room_num = #{roomNum} AND staff_name = #{staffName}")
   StaffInfo Info(ExchangeStaffRoomDTO dto);

   @Update("UPDATE staff_info SET building_num = #{selectedBuilding}, floor = #{selectedFloor}, room_num = #{selectedRoom}, room_standard = #{selectedRoomStandard}, bed_num = #{selectedBedNum}, update_by = #{updateBy}, update_time = #{updateTime} WHERE building_num = #{buildingNum} AND floor = #{floor} AND room_num = #{roomNum} AND staff_name = #{staffName}")
   void exchangeStaffRoom(ExchangeStaffRoomDTO dto);

   @Update("UPDATE staff_info SET building_num = #{buildingNum}, floor = #{floor}, room_num = #{roomNum}, room_standard = #{roomStandard}, bed_num = #{bedNum}, update_by = #{updateBy}, update_time = #{updateTime} WHERE building_num = #{selectedBuilding} AND floor = #{selectedFloor} AND room_num = #{selectedRoom} AND staff_name = #{selectedStaff}")
   void exchangeSelectedRoom(ExchangeStaffRoomDTO dto);
}
