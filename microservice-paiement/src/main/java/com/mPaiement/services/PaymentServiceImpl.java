package com.mPaiement.services;

import com.mPaiement.beans.*;
import com.mPaiement.dao.PaymentDao;
import com.mPaiement.entity.CreditcartPayment;
import com.mPaiement.entity.Payment;
import com.mPaiement.entity.PaypalPayment;
import com.mPaiement.exceptions.PaymentNotFoundException;
import com.mPaiement.proxies.MicroservicesComLigComPanier;
import com.mPaiement.proxies.MicroservicesProdCatProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements IpaymentService {
    SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private MicroservicesProdCatProxy microservicesProdCatProxy;
    @Autowired
    private MicroservicesComLigComPanier microservicesComMigComPanier;
    @Override
    public List<Payment> paymentList() {
        List<Payment> paymentList = paymentDao.findAll();
        if (paymentList.isEmpty()) throw new PaymentNotFoundException("Aucun payment");

        return paymentList;
    }

    @Override
    public void delete(OrderInfoBean orderInfoBean, int id) {
        Optional<Payment> getPayment = paymentDao.findById(id);
        if (!getPayment.isPresent()) throw new PaymentNotFoundException("Ce Payment avec L'id "+ id +" n'existe pas");
        OrderInfoBean order = microservicesComMigComPanier.getOneOrder(orderInfoBean.getId());
        for (OrderLineBean line: order.getListOrderLine()){
            ProductBean product = microservicesProdCatProxy.getOneProduct(line.getIdProductBean());
            int newQuantity = product.getQuantity() + line.getQuantity();
            product.setQuantity(newQuantity);
            microservicesProdCatProxy.updateProduct(line.getIdProductBean(),product);
        }
        paymentDao.delete(getPayment.get());
    }

    @Override
    public Payment addCreditcartPayment(int idcart, CreditcartPayment creditcartPayment) {
        if (creditcartPayment.getDateExpiration().compareTo(creditcartPayment.getdatePayment())<0){
            throw new PaymentNotFoundException("La carte de paiement a expiré depuis "+ creditcartPayment.getDateExpiration());
        }
        CartBean cart = microservicesComMigComPanier.getOneCart(idcart);
        for (CartLineBean line: cart.getListCartLine()){
            ProductBean product = microservicesProdCatProxy.getOneProduct(line.getIdProductBean());
            int newQuantity = product.getQuantity() - line.getQuantity();
            product.setQuantity(newQuantity);
            microservicesProdCatProxy.updateProduct(line.getIdProductBean(),product);
        }
        Payment saveCreditcartPayment = paymentDao.save(creditcartPayment);
        return saveCreditcartPayment;
    }
    @Override
    public Payment addPaypalPayment(int idcart, PaypalPayment paypalPayment) {
        CartBean cart = microservicesComMigComPanier.getOneCart(idcart);
        for (CartLineBean line: cart.getListCartLine()){
            ProductBean product = microservicesProdCatProxy.getOneProduct(line.getIdProductBean());
            int newQuantity = product.getQuantity() - line.getQuantity();
            product.setQuantity(newQuantity);
            microservicesProdCatProxy.updateProduct(line.getIdProductBean(),product);
        }
        Payment savepaypalPayment = paymentDao.save(paypalPayment);
        return savepaypalPayment;
    }

    @Override
    public Payment getOnePayment(int id) {
        Optional<Payment> getPayment = paymentDao.findById(id);
        if (!getPayment.isPresent()) throw new PaymentNotFoundException("Ce Payment avec l'id "+ id +" n'existe pas");
        return getPayment.get();
    }

    @Override
    public void updatePayment(Payment payment) {

    }

    @Override
    public List<Payment> paymentByTypePay(String typePay) {
        List<Payment> listPaymentByTypePay = paymentDao.findBytypePay(typePay);
        if (listPaymentByTypePay.isEmpty()) throw new PaymentNotFoundException("Il n'existe pas les payements de type "+ typePay);
        return listPaymentByTypePay;
    }

}
