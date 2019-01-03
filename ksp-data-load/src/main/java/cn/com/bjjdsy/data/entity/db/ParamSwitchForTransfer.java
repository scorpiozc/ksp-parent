package cn.com.bjjdsy.data.entity.db;

public class ParamSwitchForTransfer extends ParamSwitch {

	@Override
	public String toString() {
		return this.getSwitchCode() + "," + this.getoStationCode() + "\n" + this.getSwitchCode() + ","
				+ this.getiStationCode();
	}
}
