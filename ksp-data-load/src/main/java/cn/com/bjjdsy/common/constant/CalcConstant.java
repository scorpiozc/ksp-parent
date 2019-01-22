package cn.com.bjjdsy.common.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.com.bjjdsy.data.entity.path.Line;
import cn.com.bjjdsy.data.entity.path.Station;
import cn.com.bjjdsy.data.entity.time.AccseDateAttribute;
import cn.com.bjjdsy.data.entity.time.AccseTimeAttribute;
import cn.com.bjjdsy.data.entity.time.AccseWalkTime;
import cn.com.bjjdsy.data.entity.time.KPath;
import cn.com.bjjdsy.data.entity.time.RunMap;
import cn.com.bjjdsy.data.entity.time.RunMapKey;

public class CalcConstant {

	public static final String ACCSE_DATE_ATTRIBUTE = "/accse_date_attribute.txt";
	public static final String ACCSE_TIME_ATTRIBUTE = "/accse_time_attribute.txt";
	public static final String ACCSE_WALK_TIME = "/accse_walk_time.txt";
	public static final String K_PATH = "/k_path.txt";
	public static final String PLAN_RUN_MAP = "/route_train_record.txt";
	public static final String SECTION_BASE_INFO = "/section_base_info.txt";
	public static final String PARAM_SECTION_TCC = "/param_section_tcc.txt";

	/**
	 * key:section:date_type (accse_date_attribute) value:date_type
	 * (accse_time_attribute)
	 * 
	 */
	public static Map<String, Integer> dateTypeMap = new TreeMap<>();
	/**
	 * key:begin_time value:time_attribute (accse_time_attribute)
	 * 
	 */
	public static Map<Integer, Integer> accseTimeAttributeMap = new TreeMap<>();
	/**
	 * key:cmDate
	 */
	public static Map<String, AccseDateAttribute> accseDateAttributeDict = new TreeMap<>();
	/**
	 * key:accseStationCode:dateType
	 */
	public static Map<String, List<AccseTimeAttribute>> accseTimeAttributeDict = new TreeMap<>();
	/**
	 * key:fromAccStationCode:toAccStationCode:dateType:timeAttr:fromDirect:toDirect
	 */
	public static Map<String, AccseWalkTime> accseWalkTimeDict = new TreeMap<>();
	/**
	 * key:fromStation:toStation,value:direct
	 */
	public static Map<String, Integer> sectionDict = new HashMap<>();
	/**
	 * key:pltfId:direction
	 */
	public static Map<String, List<RunMapKey>> runMapKeyMap = new TreeMap<>();
	/**
	 * key:lineNo:tripNo sub key:pltfId
	 */
	public static Map<String, Map<String, RunMap>> runMapMap = new HashMap<>();

	public static List<KPath> kPathList = new ArrayList<>();
	/**
	 * key:tccStationCode
	 */
	public static Map<String, String> paramSectionTccMap = new HashMap<>();

	public static Map<String, List<String>> deptimeMap = new TreeMap<>();

	// --- path clac
	public static final String STATION_BASE_INFO = "/station_base_info.txt";
	public static final String LINE_BASE_INFO = "/line_base_info.txt";
//	public static final String SECTION_BASE_INFO = "section_base_info.txt";
	public static final String TRANSFER_LINE_WALKTIME_INFO = "/transfer_line_walktime_info.txt";
	public static final String TRANSFER_BASE_INFO = "/transferstation_base_info.txt";
	public static final String STATION_PARKTIME = "/station_parktime.txt";
	public static final String LINE_DEPART_INTERVAL_TIME = "/line_depart_interval_time.txt";

	// output filenames
	public static final String OUTPUT = "shortest_distances_";
	public static final int MAX_LINE = 100;
	public static final int MAX_STATION = 10000;
	public static final boolean PATHOUTPUT = true;
	public static final boolean DEPART_ALPHA_ON = false;
	public static final double DEPART_WEIGHT = 0.5;

	public static int sectionId = 0;
	public static int stationCounts = 0;
	public static Map<Integer, String> stationDict = new TreeMap<>();
	public static Map<Integer, String> lineDict = new HashMap<>();
	public static Map<Integer, ArrayList<Integer>> transferDict = new HashMap<>();
	public static Map<String, Integer> parktimesDict = new HashMap<>();
	public static Map<Integer, Integer> departIntervalTimesDict = new HashMap<>();
	public static Map<String, Integer> fakeTransferDict = new HashMap<>();
	public static Station[] stations = new Station[MAX_STATION];
	public static Line[] lines = new Line[MAX_LINE];
	public static int[] stationCodes = new int[MAX_STATION];
	public static int[] stationIndexes = new int[MAX_STATION];

}
