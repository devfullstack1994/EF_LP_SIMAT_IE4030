/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ef_lp_simat_ie4030.controller;

import com.mycompany.ef_lp_simat_ie4030.dao.AlumnoDAO;
import com.mycompany.ef_lp_simat_ie4030.dao.DocenteDAO;
import com.mycompany.ef_lp_simat_ie4030.dao.impl.AlumnoDAOImpl;
import com.mycompany.ef_lp_simat_ie4030.dao.impl.DocenteDAOImpl;
import com.mycompany.ef_lp_simat_ie4030.model.Alumno;
import com.mycompany.ef_lp_simat_ie4030.model.Docente;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "ReporteServlet", urlPatterns = {"/reportes", "/reportes/export"})
public class ReporteServlet extends HttpServlet {

    private final AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
    private final DocenteDAO docenteDAO = new DocenteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sp = request.getServletPath();

        // Seguridad por sesión
        HttpSession ses = request.getSession(false);
        if (ses == null || ses.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 1) Pantalla de reportes
        if (sp.equals("/reportes")) {
            request.getRequestDispatcher("/admin/reportes.jsp").forward(request, response);
            return;
        }

        // 2) Exportación
        if ("/reportes/export".equals(sp)) {
            String tipo = request.getParameter("tipo"); // alumnos | docentes
            String fmt = request.getParameter("fmt");  // xlsx (por ahora)

            if ("alumnos".equals(tipo) && "xlsx".equals(fmt)) {
                exportAlumnosExcel(response);
                return;
            }
            if ("docentes".equals(tipo) && "xlsx".equals(fmt)) {
                exportDocentesExcel(response);
                return;
            }

           //pdf aun no implementado
            if (("alumnos".equals(tipo) || "docentes".equals(tipo)) && "pdf".equals(fmt)) {
                response.sendError(501, "PDF aún no implementado");
                return;
            }

            response.sendError(400, "Parámetros inválidos");
        }
    }

   //alumnos xls
    private void exportAlumnosExcel(HttpServletResponse response) {
        try {
            List<Alumno> alumnos = alumnoDAO.listAll();

            Workbook wb = new XSSFWorkbook();
            Sheet sh = wb.createSheet("Alumnos");

            CellStyle headerStyle = wb.createCellStyle();
            Font headerFont = wb.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            String[] headers = {"ID", "DNI", "Apellidos", "Nombres", "Grado", "Sección", "Estado"};
            Row headerRow = sh.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell c = headerRow.createCell(i);
                c.setCellValue(headers[i]);
                c.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Alumno a : alumnos) {
                Row r = sh.createRow(rowNum++);
                r.createCell(0).setCellValue(a.getIdAlumno());
                r.createCell(1).setCellValue(a.getDni());
                r.createCell(2).setCellValue(a.getApellidos());
                r.createCell(3).setCellValue(a.getNombres());
                r.createCell(4).setCellValue(a.getGrado() == null ? "" : a.getGrado());
                r.createCell(5).setCellValue(a.getSeccion() == null ? "" : a.getSeccion());
                r.createCell(6).setCellValue(a.getEstado() == 1 ? "Activo" : "Inactivo");
            }

            for (int i = 0; i < headers.length; i++) {
                sh.autoSizeColumn(i);
            }

            setExcelDownloadHeaders(response, "reporte_alumnos.xlsx");

            try (OutputStream os = response.getOutputStream()) {
                wb.write(os);
            }
            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //docentes xls
    private void exportDocentesExcel(HttpServletResponse response) {
        try {
            List<Docente> docentes = docenteDAO.listAll();

            Workbook wb = new XSSFWorkbook();
            Sheet sh = wb.createSheet("Docentes");

            CellStyle headerStyle = wb.createCellStyle();
            Font headerFont = wb.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            String[] headers = {"ID", "DNI", "Apellidos", "Nombres", "Especialidad", "Teléfono", "Email", "Estado"};
            Row headerRow = sh.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell c = headerRow.createCell(i);
                c.setCellValue(headers[i]);
                c.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Docente d : docentes) {
                Row r = sh.createRow(rowNum++);
                r.createCell(0).setCellValue(d.getIdDocente());
                r.createCell(1).setCellValue(d.getDni());
                r.createCell(2).setCellValue(d.getApellidos());
                r.createCell(3).setCellValue(d.getNombres());
                r.createCell(4).setCellValue(d.getEspecialidad() == null ? "" : d.getEspecialidad());
                r.createCell(5).setCellValue(d.getTelefono() == null ? "" : d.getTelefono());
                r.createCell(6).setCellValue(d.getEmail() == null ? "" : d.getEmail());
                r.createCell(7).setCellValue(d.getEstado() == 1 ? "Activo" : "Inactivo");
            }

            for (int i = 0; i < headers.length; i++) {
                sh.autoSizeColumn(i);
            }

            setExcelDownloadHeaders(response, "reporte_docentes.xlsx");

            try (OutputStream os = response.getOutputStream()) {
                wb.write(os);
            }
            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 //headers excel
    private void setExcelDownloadHeaders(HttpServletResponse response, String fileName) throws Exception {
        String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replace("+", "%20");
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encoded);
    }
}
