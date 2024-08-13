package site.yingjian.serialization.demo.gson;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Vehicle {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleStatus(1);
        vehicle.setVehicleChargingStatus(3);
        vehicle.setVehicleWorkingModel(1);
        vehicle.setVehicleSpeed(80);
        vehicle.setVehicleMileage(209854);


        Gson gson = new Gson();
        String vehicleJson = gson.toJson(vehicle);
        log.info(vehicleJson);
    }

    /* 整车数据 start */

    /**
     * 车辆状态
     * 0x01：车辆启动状态；0x02：熄火；0x03：其他状态；"0xFE”表示异常，"0xFF”表示无效
     */
    @SerializedName("vehicle_status")
    private int vehicleStatus;

    /**
     * 充电状态
     * 0x01：停车充电；0x02：行驶充电；0x03：未充电状态；0x04：充电完成；"0xFE”表示异常，"0xFF”表示无效
     */
    @SerializedName("charging_status")
    private int vehicleChargingStatus;

    /**
     * 运行模式
     * 0x01：纯电；0x02：混动；0x03：燃油；0xFE表示异常；0xFF表示无效
     */
    @SerializedName("woking_model")
    private int vehicleWorkingModel;

    /**
     * 车速
     * 有效值范围：0~2200(表示0km/h~220km/h)，最小计量单元：0.1km/h，"0xFF，0xFE”表示异常，"0xFF，0xFF”表示无效
     */
    @SerializedName("speed")
    private int vehicleSpeed;

    /**
     * 累计里程
     * 有效值范围：0~9999999(表示0km~999999.9km)，最小计量单元：0.1km。"0xFF，0xFF，0xFF，0xFE表示异常，0xFF，0xFF，0xFF，0xFF”表示无效
     */
    @SerializedName("mileage")
    private int vehicleMileage;

    /**
     * 总电压
     * 有效值范围：0~10000(表示0V~1000V)，最小计量单元：0.1V，"0xFF，0xFE”表示异常，"0XFF，0xFF”表示无效
     */
    @SerializedName("total_voltage")
    private int vehicleTotalVoltage;

    /**
     * 总电流
     * 有效值范围：0—20000(偏移量1000A，表示一1000A~+1000A)，最小计量单元：0.1A，"0xFF，0xFE”表示异常，"0xFF，0xFF”表示无效
     */
    @SerializedName("total_current")
    private int vehicleTotalCurrent;

    /**
     * SOC
     * 有效值范围：0~100(表示0%~100%)，最小计量单元：1%，"0xFE”表示异常，"0xFF”表示无效
     */
    @SerializedName("SOC")
    private int vehicleSoc;

    /**
     * DC-DC-状态
     * 0X01：工作；0x02：断开，"0xFE”表示异常，"0xFF”表示无效
     */
    @SerializedName("dcdc_state")
    private int vehicleDcdcState;

    /**
     * 挡位
     * 挡位：0：空挡、1：1档、2：2档、3：3档、4：4档、5：5档、6：6档………13：倒挡、14：自动D挡、15：停车P档；
     * 当值大于15时，需要解析(当值在[16,32)区间时,(值-16)即为对应档位；当值在[32,48)区间时,(值-32)即为对应档位)
     */
    @SerializedName("gears_position")
    private int vehicleGearsPosition;

    /**
     * 绝缘电阻
     * 有效值范围：0~60000(表示0kΩ~60000kΩ)，最小计量单元：1kΩ
     */
    @SerializedName("bir")
    private int vehicleBir;

    /* 整车数据 end */



}
