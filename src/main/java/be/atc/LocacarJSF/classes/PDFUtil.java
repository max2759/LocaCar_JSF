package be.atc.LocacarJSF.classes;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import be.atc.LocacarJSF.services.ContractInsurancesServices;
import be.atc.LocacarJSF.services.ContractInsurancesServicesImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import utils.JsfUtils;

import javax.faces.context.FacesContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * @author Younes Arifi
 * This class is for generate PDF
 */
public class PDFUtil {

    public static void generatePDF(OrdersEntity ordersEntity, List<ContractsEntity> contractsEntities, double priceOrder) {
        String namePDF = ordersEntity.getUsersByIdUsers().getFirstname() + "-" + ordersEntity.getUsersByIdUsers().getLastname() + "_" + "Order" + ordersEntity.getId() + "_" + ordersEntity.getOrderStatut() + ".pdf";
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ContractInsurancesServices contractInsurancesServices = new ContractInsurancesServicesImpl();

        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(Constants.FILE_OUT_PUT_STREAM + namePDF));
            doc.open();

            doc.add(new Paragraph(ordersEntity.getUsersByIdUsers().getLastname() + " " + ordersEntity.getUsersByIdUsers().getLastname()));
            if (ordersEntity.getUsersByIdUsers().getVatNumber() != null) {
                doc.add(new Paragraph(ordersEntity.getUsersByIdUsers().getVatNumber()));
            }

            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.orderNum") + ordersEntity.getId()));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.orderDate") + ordersEntity.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.orderStatus") + ordersEntity.getOrderStatut()));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.userNum") + ordersEntity.getUsersByIdUsers().getId()));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.userUserName") + ordersEntity.getUsersByIdUsers().getUsername()));

            for (ContractsEntity c : contractsEntities) {
                doc.add(new Paragraph("--------------------------------------------------------------"));
                doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.contractType") + c.getContractTypesByIdContractType().getLabel()));
                doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.contractDateStart") + c.getDateStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                    doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.contractDateEnd") + c.getDateEnd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                }
                doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.contractModel") + c.getCarsByIdCars().getModelsByIdModels().getBrandsByIdBrands().getLabel()));
                doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.contractMarque") + c.getCarsByIdCars().getModelsByIdModels().getLabel()));
                doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.contractCarsType") + c.getCarsByIdCars().getCarsTypesByIdCarsTypes().getLabel()));
                doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.priceCar") + c.getCarPrice() + "€"));

                if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                    ContractInsurancesEntity contractInsurancesEntity = contractInsurancesServices.findByIdContract(c.getId());
                    doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.insuranceLabel") + contractInsurancesEntity.getInsurancesByIdInsurance().getLabel()));
                    doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.insurancePrice") + contractInsurancesEntity.getInsurancesByIdInsurance().getPrice() + "€" + JsfUtils.returnMessage(locale, "pdf.insuranceMonth")));
                }
            }

            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph("--------------------------------------------------------------"));
            doc.add(new Paragraph(JsfUtils.returnMessage(locale, "pdf.total") + priceOrder + "€"));
            doc.add(new Paragraph("--------------------------------------------------------------"));

            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
