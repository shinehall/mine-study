package tech.excel.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chanjet.gongzuoquan.utils.CommonUtils;

/**
 * excel 工具类
 * 
 */
public class ExcelUtil {

  private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

  /**
   * 
   * @param out
   * @param datas
   *          封装着Object[]的列表, 一般是String内容.
   * @param title
   *          每个sheet里的标题.
   * @param sheetName
   *          sheet名称
   */
  @SuppressWarnings("rawtypes")
  public static void writeExcel(OutputStream out, List datas, String[] title, List datas2,
      List datas3, String[] title2, String sheetName, String titleName4Time) {
    if (datas == null) {
      throw new IllegalArgumentException("写excel流需要List参数!");
    }
    try {
      WritableWorkbook workbook = Workbook.createWorkbook(out);
      WritableSheet ws = workbook.createSheet(sheetName, 0);// sheet表1
      int rowNum = 0; // 要写的行
      if (StringUtils.isNotBlank(titleName4Time)) {
        putRow(ws, 0, title, titleName4Time);// 压入标题
        rowNum = 1;
      }
      if (title != null) {
        putRow(ws, 1, title, titleName4Time);// 压入标题
        rowNum = 2;
      }
      for (int i = 0; i < datas.size(); i++, rowNum++) {// 写sheet
        Object[] cells = (Object[]) datas.get(i);
        putRow(ws, rowNum, cells, titleName4Time); // 压一行到sheet
      }

      if (title2 != null && datas2.size() > 0) {// sheet表2
        ws = workbook.createSheet("签到详情", 1);
        putRow2(ws, 0, title2, null);// 压入标题
        rowNum = 1;
        for (int i = 0; i < datas2.size(); i++, rowNum++) {// 写sheet表2
          Object[] cells = (Object[]) datas2.get(i);
          Object[] cells3 = (Object[]) datas3.get(i);
          putRow2(ws, rowNum, cells, cells3); // 压一行到sheet
        }
      }
      workbook.write();
      workbook.close(); // 一定要关闭, 否则没有保存Excel
    } catch (RowsExceededException e) {
      log.error("jxl write RowsExceededException: " + e.getMessage());
    } catch (WriteException e) {
      log.error("jxl write WriteException: " + e.getMessage());
    } catch (IOException e) {
      log.error("jxl write file i/o exception!, cause by: " + e.getMessage());
    }
  }

  /**
   * 获取excel属性
   * 
   * @param inS
   *          InputStream
   * @return String[]
   */
  public static String[] getExcelAttributes(InputStream inS) {
    try {
      Workbook workbook = Workbook.getWorkbook(inS);
      Sheet sheet = workbook.getSheet(0);
      // int rows = getRows(sheet);
      int columns = getColumns(sheet);
      return getAttribute(sheet, 0, columns, 0);
    } catch (BiffException e) {
      log.error("jxl BiffException: " + e.getMessage());
      // e.printStackTrace();
    } catch (IOException e) {
      log.error("jxl IOException: " + e.getMessage());
      // e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取ins中指定属性的值
   * 
   * @param inS
   *          excel的输入流
   * @param attributes
   *          需要获取的属性(如果为空就获取全部属性)
   */
  public static String[][] getExcelContents(InputStream inS, String[] attributes) {
    String[][] contents = null;
    try {
      Workbook workbook = Workbook.getWorkbook(inS);
      Sheet sheet = workbook.getSheet(0);
      int rows = getRows(sheet);
      int columns = getColumns(sheet);
      String[] attributesAll = getAttribute(sheet, 0, columns, 0);
      if (attributes == null) {
        attributes = attributesAll;
      }
      String[][] cellsArray = getCells(sheet, 1, rows, 0, columns);
      contents = getContentsFromFixAttri(attributes, attributesAll, cellsArray);

    } catch (BiffException e) {
      log.error("jxl BiffException: " + e.getMessage());
      // e.printStackTrace();
    } catch (IOException e) {
      log.error("jxl IOException: " + e.getMessage());
      // e.printStackTrace();
    }
    return contents;
  }

  /**
   * 返回list list
   * 
   * @param filePath
   *          filePath
   * @param sheetNum
   *          sheetNum
   * @return List
   */
  public static List<List<String>> readListExcelFile(String filePath, int sheetNum) {
    List<List<String>> ls = new ArrayList<>();
    Workbook book = null;
    try {
      // 读Excel文件
      book = Workbook.getWorkbook(new File(filePath));
      // 获得工作表个数
      Sheet sheet = book.getSheet(sheetNum);
      for (int i = 0; i < sheet.getRows(); i++) {
        // 获得行
        Cell[] row = sheet.getRow(i);
        // Map<String, String> rowMap = new HashMap<String, String>();
        List<String> rowMap = new ArrayList<>();
        for (Cell aRow : row) {

          // 获得单元格内容
          String content = aRow.getContents().trim();
          if (!StringUtils.isEmpty(content)) {
            // 因为从0开始,所以要+1
            rowMap.add(content);
          }
        }
        if (!rowMap.isEmpty()) {
          ls.add(rowMap);
        }
      }
    } catch (BiffException e) {
      log.error("BiffException", e);
    } catch (IOException e) {
      log.error("IOException", e);
    } finally {
      if (book != null) {
        book.close();
      }
    }
    return ls;
  }

  public static List<Map<String, String>> readExcelFile(String filePath, int sheetNum) {
    List<Map<String, String>> ls = new ArrayList<>();
    Workbook book = null;
    try {
      // 读Excel文件
      book = Workbook.getWorkbook(new File(filePath));
      // 获得工作表个数
      Sheet sheet = book.getSheet(sheetNum);
      for (int i = 0; i < sheet.getRows(); i++) {
        // 获得行
        Cell[] row = sheet.getRow(i);
        Map<String, String> rowMap = new HashMap<>();
        for (int j = 0; j < row.length; j++) {
          // 获得单元格内容
          String content = row[j].getContents();
          if (StringUtils.isNotBlank(content)) {
            // 因为从0开始,所以要+1
            rowMap.put(String.valueOf(j + 1), content);
          }
        }
        if (!rowMap.isEmpty()) {
          ls.add(rowMap);
        }
      }
    } catch (BiffException | IOException e) {
      e.printStackTrace();
    } finally {
      if (book != null) {
        book.close();
      }
    }
    return ls;
  }

  /**
   * 获取contentsAll中指定属性的值
   * 
   * @param attributes
   *          需要获取的属性
   * @param attributeAll
   *          所有属性
   * @param contentsAll
   *          所有值
   */
  public static String[][] getContentsFromFixAttri(String[] attributes, String[] attributeAll,
      String[][] contentsAll) {
    String[][] contents;
    List<Integer> indexS = new ArrayList<>();
    if (attributes != null && attributeAll != null && attributeAll.length >= attributes.length) {
      for (String attribute : attributes) {
        for (int j = 0; j < attributeAll.length; j++) {
          if (attribute.equals(attributeAll[j])) {
            indexS.add(j);
          }
        }
      }
    }
    int columnCount = indexS.size();
    int rowCount = contentsAll.length;
    contents = new String[rowCount][columnCount];
    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < columnCount; j++) {
        contents[i][j] = contentsAll[i][indexS.get(j)];
      }
    }
    return contents;
  }

  /**
   * 获取属性数组
   * 
   * @param sheet
   *          工作薄
   * @param startColumn
   *          开始列
   * @param endColumn
   *          结束列
   * @param row
   *          属性所在行数
   */
  public static String[] getAttribute(Sheet sheet, int startColumn, int endColumn, int row) {
    String[] attributes = new String[endColumn - startColumn];
    int maxColumn = getColumns(sheet);
    int maxRow = getRows(sheet);
    if (row < maxRow) {
      for (int i = startColumn; i < endColumn && i < maxColumn; i++) {
        Cell attribute = sheet.getCell(i, row);
        attributes[i] = attribute.getContents();
      }
    }

    return attributes;
  }

  /**
   * 获取每行单元格数组
   * 
   * @param sheet
   *          工作薄
   * @param startrow
   *          行数
   * @param endrow
   *          结束行
   * @param startcol
   *          开始列
   * @param endcol
   *          结束列
   * @return 每行单元格数组
   */
  public static String[][] getCells(Sheet sheet, int startrow, int endrow, int startcol, int endcol) {
    String[][] cellArray = new String[endrow - startrow][endcol - startcol];
    int maxRow = getRows(sheet);
    int maxCos = getColumns(sheet);
    for (int i = startrow; i < endrow && i < maxRow; i++) {

      for (int j = startcol; j < endcol && j < maxCos; j++) {

        cellArray[i - startrow][j - startcol] = sheet.getCell(j, i).getContents();
      }

    }
    return cellArray;
  }

  /**
   * 获取工作薄总行数
   * 
   * @param sheet
   *          工作薄
   * @return 工作薄总行数
   */
  public static int getRows(Sheet sheet) {
    return sheet == null ? 0 : sheet.getRows();
  }

  /**
   * 获取最大列数
   * 
   * @param sheet
   *          工作薄
   * @return 总行数最大列数
   */
  public static int getColumns(Sheet sheet) {
    return sheet == null ? 0 : sheet.getColumns();
  }

  /**
   * 获取每行单元格数组
   * 
   * @param sheet
   *          工作薄
   * @param row
   *          行数
   * @return 每行单元格数组
   */
  public static Cell[] getRows(Sheet sheet, int row) {
    return sheet == null || sheet.getRows() < row ? null : sheet.getRow(row);
  }

  /**
   * 写一行
   * 
   * @param ws
   *          WritableSheet
   * @param rowNum
   *          rowNum
   * @param cells
   *          cells
   * @throws RowsExceededException
   * @throws WriteException
   */
  private static void putRow(WritableSheet ws, int rowNum, Object[] cells, String titleName4time)
      throws WriteException {
    int cellLength = cells.length;
    for (int j = 0; j < cellLength; j++) {// 写一行

      try {
        boolean isNumber = false;
        if (cells[j] instanceof Integer || cells[j] instanceof Double) {
          isNumber = true;
        }
        // 数字字体
        WritableCellFormat intFormat = new WritableCellFormat(NumberFormats.INTEGER);
        // 设置字体格式为excel支持的格式
        WritableFont font12 = new WritableFont(WritableFont.createFont("微软雅黑"), 12);// 微软雅黑
        WritableFont fontSong12 = new WritableFont(WritableFont.createFont("宋体"), 12,
            WritableFont.BOLD);// 宋体
        WritableCellFormat format;
        if (rowNum == 0) {// 表头
          format = new WritableCellFormat(fontSong12);
          format.setAlignment(Alignment.LEFT);
          format.setBorder(Border.ALL, BorderLineStyle.NONE);
        } else {
          if (isNumber) {
            format = new WritableCellFormat(intFormat);
          } else {
            format = new WritableCellFormat(font12);
          }
          format.setAlignment(Alignment.CENTRE);
          format.setBorder(Border.ALL, BorderLineStyle.THIN);
          format.setWrap(true);// 自动换行
        }

        Label cell = null;
        jxl.write.Number number = null;
        if (rowNum == 0) {// 表头
          if (j == cellLength - 6) {// 日期显示在表格右上方第一行
            cell = new Label(j, rowNum, titleName4time, format);
          } else {
            cell = new Label(j, rowNum, " ", format);
          }
        } else {
          if (isNumber) {
            number = new jxl.write.Number(j, rowNum, Double.parseDouble(cells[j].toString()),
                format);
          } else {
            cell = new Label(j, rowNum, "" + cells[j], format);
          }
        }

        if (rowNum == 0) {// 表头
          if ((j == cellLength - 1) && cellLength >= 6) {
            ws.mergeCells(cellLength - 6, 0, cellLength - 1, 0);
          }
          ws.setRowView(rowNum, 30 * 20);// 行高30
        } else if (rowNum == 1) {// 表格标题
          ws.setRowView(rowNum, 46 * 20);// 行高46
        } else {
          ws.setRowView(rowNum, 38 * 20);// 行高38
        }

        if (0 == j) {// 左边第一列设置
          if (StringUtils.isBlank(cells[j] + "")) {// 左边第一列设置
            WritableCellFeatures cellFeatures = new WritableCellFeatures();
            cellFeatures.setComment("客户名为空");
            cell.setCellFeatures(cellFeatures);
          }
        }
        if (j == cellLength-1) {// 最后一行合计设置列宽较大
          ws.setColumnView(j, 10);// 列宽15
        } else {
          ws.setColumnView(j, 8);// 列宽8
        }
        if (rowNum == 0) {
          ws.addCell(cell);
        } else {
          if (isNumber) {
            ws.addCell(number);
          } else {
            ws.addCell(cell);
          }
        }

      } catch (Exception e) {
        log.error("putRow Exception: " + e.getMessage());
      }

    }
  }

  /**
   * 写一行
   * 
   * @param ws
   *          WritableSheet
   * @param rowNum
   *          rowNum
   * @param cells
   *          cells
   * @throws RowsExceededException
   * @throws WriteException
   */
  private static void putRow2(WritableSheet ws, int rowNum, Object[] cells, Object[] cells3)
      throws WriteException {
    for (int j = 0; j < cells.length; j++) {// 写一行

      try {
        // 设置字体格式为excel支持的格式
        WritableFont font0 = new WritableFont(WritableFont.createFont("微软雅黑"), 16,
            WritableFont.BOLD, false);// 微软雅黑,带下划线
        WritableFont font1 = new WritableFont(WritableFont.createFont("微软雅黑"), 12);// 微软雅黑
        WritableFont redFont = new WritableFont(WritableFont.createFont("微软雅黑"), 12,
            WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
        WritableCellFormat format1 = new WritableCellFormat(font1);
        if (rowNum == 0) {
          format1 = new WritableCellFormat(font0);
          // format1.setBackground(jxl.format.Colour.BRIGHT_GREEN);// 设置背景颜色
        } else {
          if (j == 6) {
            String status = String.valueOf(cells[j]);
            if ("定位失败".equals(status)) {// 状态是定位失败字体变红
              format1 = new WritableCellFormat(redFont);
            }
          }
        }

        String uri = null;
        if (rowNum > 0 && j == 4) {
          uri = String.valueOf(cells3[0]);
        }
        boolean hasLink = (rowNum > 0 && j == 4 && CommonUtils.checkUri(uri));// 含有合法的图片地址
        if (hasLink) {
          ws.addHyperlink(new WritableHyperlink(j, rowNum, new URL(uri))); // 关键的建立后面sheet连接的一句
          format1.setWrap(true);// 自动换行
        }
        format1.setAlignment(Alignment.CENTRE);
        format1.setBorder(Border.ALL, BorderLineStyle.THIN);
        Label cell = new Label(j, rowNum, (hasLink) ? (cells[j] + "\r\n" + "图片:" + uri)
            : ("" + cells[j]), format1);

        if (hasLink) {
          WritableCellFeatures cellFeatures = new WritableCellFeatures();
          cellFeatures.setComment("点击打开签到图片");
          cell.setCellFeatures(cellFeatures);
        }
        if (hasLink) {
          ws.setColumnView(j, 60);// 列宽
        } else if (j == 4 || j == 5) {
          ws.setColumnView(j, 30);
        } else if (j == 1 || j == 2) {
          ws.setColumnView(j, 15);
        } else {
          ws.setColumnView(j, 20);
        }

        if (rowNum == 0) {
          ws.setRowView(rowNum, 30 * 20);// 行高30
        } else {
          if (hasLink) {
            ws.setRowView(rowNum, 200 * 20);// 含有图片链接时增加行高
          } else {
            ws.setRowView(rowNum, 50 * 20);// 行高40
          }
        }
        ws.addCell(cell);

      } catch (Exception e) {
        log.error("putRow Exception: " + e);
      }

    }
  }

  /**
   * 写一行
   * 
   * @param ws
   *          WritableSheet
   * @param rowNum
   *          rowNum
   * @param cells
   *          cells
   * @throws RowsExceededException
   * @throws WriteException
   */
  public static void putRow(WritableSheet ws, int rowNum, WritableCell[] cells)
      throws WriteException {
    for (WritableCell cell : cells) {// 写一行
      ws.addCell(cell);
    }
  }

  public static void main(String[] args) throws IOException {

    File file = new File("/Users/chenxinghua/Documents/test.xls");
    if (file.createNewFile()) {
     // OutputStream out = new FileOutputStream(file);
      List<Object[]> list = new ArrayList<>();

      Object[] obj;
      for (int i = 0; i < 100; i++) {
        obj = new Object[] { i, i + "名称" + i, "jdslkfj" + i };
        list.add(obj);
      }
      // writeExcel(out, list, new String[] { "我的测试" },"");
    }
  }
}
