package tech.excel.traning;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import com.chanjet.gongzuoquan.utils.DateUtil;
import tech.excel.util.ExcelUtil;

public class ExcportExcel {

  
  // 按天统计输出到Excel
  public void dayToExcel(Map<String, Object> data, HttpServletResponse response, String displayName,
      String fieldName, String fileName, String titleName4Time, Date firstDay, Date lastDay)
          throws IOException {
    List<String> titles = new ArrayList<String>();
    titles.add(displayName);
    if ("empName".equals(fieldName)) {// 按姓名排序时增加显示部门
      titles.add("部门");
    }
    fillTitles(firstDay, lastDay, titles);
    titles.add("本月合计");
    String[] titleNew = new String[titles.size()];
    int i = 0;
    for (String til : titles) {
      titleNew[i++] = til;
    }
    exportDayToExcel(data, response, titleNew, fieldName, fileName, titleName4Time, firstDay,
        lastDay);
  }
  
  @SuppressWarnings("unchecked")
  private void exportDayToExcel(Map<String, Object> data, HttpServletResponse response,
      String[] title, String fieldName, String fileName, String titleName4Time, Date firstDay,
      Date lastDay) throws IOException {
    List<Map<String, Object>> dataList = (List<Map<String, Object>>) data.get("list");
    OutputStream out = null;
    if (true) {
      File file = new File("d:/" + fileName + ".xls");
      if (file.createNewFile()) {
        out = new FileOutputStream(file);
      } else {
        file.delete();
        file.createNewFile();
        out = new FileOutputStream(file);
      }
    } else {
//      out = new BufferedOutputStream(response.getOutputStream());
    }
    response.reset(); // 清空输出流
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName
        .getBytes("gb2312"), "ISO8859-1") + ".xls");// 设定输出文件头
    response.setContentType("application/msexcel");// 定义输出类型
    List<Object[]> list = new ArrayList<>();
    for (int i = 0; i < dataList.size(); i++) {
      Map<String, Object> map = dataList.get(i);
      List<Object> titles = new ArrayList<Object>();
      String startDateTitle = DateUtil.date2Str(firstDay, DateUtil.DATE_PATTERN_yyyyMMdd);// 取开始日期
      String endDateTitle = DateUtil.date2Str(lastDay, DateUtil.DATE_PATTERN_yyyyMMdd);// 取结束日期
      titles.add(map.get(fieldName));
      if ("empName".equals(fieldName)) {// 按姓名排序时增加显示部门
        titles.add(map.get("deptName"));
      }
      int dayIndex = 1;
      do {
        titles.add(((Map<String, Object>) map.get(startDateTitle)).get("count"));
        // 取下一天日期
        startDateTitle = DateUtil.date2Str(DateUtil.getDate4AndDays(firstDay, dayIndex++),
            DateUtil.DATE_PATTERN_yyyyMMdd);
      } while (Integer.parseInt(endDateTitle) >= Integer.parseInt(startDateTitle));
      titles.add(map.get("monthCount"));
      list.add(titles.toArray());
    }
    List<Map<String, Object>> detailData = (List<Map<String, Object>>) data.get("detailData");
    String[] detailTitle = new String[] { "姓名", "部门", "日期", "签到时间", "客户", "汇报", "位置信息", "状态" };
    List<Object[]> detailList = new ArrayList<>();
    List<Object[]> detailUriList = new ArrayList<>();
    for (int i = 0; i < detailData.size(); i++) {
      Map<String, Object> map = detailData.get(i);
      Object[] detailObj = new Object[] { map.get("userName"), map.get("deptName"), map.get("date"),
          map.get("time"), map.get("addrMemo"), map.get("content"), map.get("location"), map.get(
              "status") };
      detailList.add(detailObj);
      Object[] detailObjUri = new Object[] { map.get("uri") };
      detailUriList.add(detailObjUri);
    }
    ExcelUtil.writeExcel(out, list, title, detailList, detailUriList, detailTitle, "月签到统计表",
        titleName4Time);
  }

  private void fillTitles(Date firstDay, Date lastDay, List<String> titles) {
    String startDateTitle = DateUtil.date2Str(firstDay, DateUtil.DATE_PATTERN_yyyyMMdd);// 取开始日期
    String endDateTitle = DateUtil.date2Str(lastDay, DateUtil.DATE_PATTERN_yyyyMMdd);// 取结束日期
    int i = 1;
    do {
      titles.add(DateUtil.date2Str(DateUtil.str2Date(startDateTitle,
          DateUtil.DATE_PATTERN_yyyyMMdd), "MM.dd") + " " + getDayTitle(startDateTitle));
      // 取下一天日期
      startDateTitle = DateUtil.date2Str(DateUtil.getDate4AndDays(firstDay, i++),
          DateUtil.DATE_PATTERN_yyyyMMdd);
    } while (Integer.parseInt(endDateTitle) >= Integer.parseInt(startDateTitle));
  }
  
  /** 获取星期几
   * @param startDateTitle
   * @return */
  private String getDayTitle(String date) {
    int day = DateUtil.getDayOfWeek(date, DateUtil.DATE_PATTERN_yyyyMMdd);
    switch (day) {
    case 1:
      return "星期一";
    case 2:
      return "星期二";
    case 3:
      return "星期三";
    case 4:
      return "星期四";
    case 5:
      return "星期五";
    case 6:
      return "星期六";
    case 7:
      return "星期日";
    }
    return "";
  }
}
