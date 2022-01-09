package kr.mashup.branding.recruitpoc.infra.excel

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import javax.servlet.http.HttpServletResponse

/**
 * https://bamdule.tistory.com/232
 */
object ExcelUtil {
    private var rowNum = 0

    //File로 만들 경우
    @Throws(FileNotFoundException::class, IOException::class)
    fun createExcelToFile(datas: List<Map<String, Any>>, filepath: String?) {
        //workbook = new HSSFWorkbook(); // 엑셀 97 ~ 2003
        //workbook = new XSSFWorkbook(); // 엑셀 2007 버전 이상
        val workbook: Workbook = SXSSFWorkbook() // 성능 개선 버전
        val sheet: Sheet = workbook.createSheet("데이터")
        rowNum = 0
        createExcel(sheet, datas)
        val fos = FileOutputStream(File(filepath))
        workbook.write(fos)
        workbook.close()
    }

    //HttpServletResponse 경우
    @Throws(IOException::class)
    fun createExcelToResponse(datas: List<Map<String, Any>>, filename: String?, response: HttpServletResponse) {
        val workbook: Workbook = SXSSFWorkbook() // 성능 개선 버전
        val sheet: Sheet = workbook.createSheet("Sheet")
        rowNum = 0
        createExcel(sheet, datas)

        // 컨텐츠 타입과 파일명 지정
        response.contentType = "application/vnd.ms-excel"
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", filename))
        workbook.write(response.outputStream)
        workbook.close()
    }

    //엑셀 생성
    private fun createExcel(sheet: Sheet, datas: List<Map<String, Any>>) {

        //데이터를 한개씩 조회해서 한개의 행으로 만든다.
        for (data in datas) {
            //row 생성
            val row: Row = sheet.createRow(rowNum++)
            var cellNum = 0

            //map에 있는 데이터를 한개씩 조회해서 열을 생성한다.
            for (key in data.keys) {
                //cell 생성
                val cell: Cell = row.createCell(cellNum++)

                //cell에 데이터 삽입
                cell.setCellValue(data[key].toString())
            }
        }
    }
}