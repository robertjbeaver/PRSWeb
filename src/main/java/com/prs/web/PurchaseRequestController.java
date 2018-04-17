package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestRepository;
import com.prs.util.PRSMaintenanceReturn;

@Controller
@RequestMapping(path="/PurchaseRequest")
public class PurchaseRequestController extends BaseController {
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;

	@GetMapping(path="/List")
	public @ResponseBody Iterable<PurchaseRequest> getAllPurchaseRequests(){
		return purchaseRequestRepository.findAll();
	}
	
	@GetMapping(path="/Get")
	public @ResponseBody List<PurchaseRequest> getPurchaseRequest(@RequestParam int id) {
		Optional<PurchaseRequest> p = purchaseRequestRepository.findById(id);
		return getReturnArray(p);
	}
	@PostMapping(path="/Add")
	public @ResponseBody PRSMaintenanceReturn addNewPurchaseRequest (@RequestBody PurchaseRequest purchaseRequest) {
		try {
			purchaseRequestRepository.save(purchaseRequest);
		}
		catch (Exception e) {
			purchaseRequest = null;	
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequest);
	}
	@GetMapping(path="/Remove")
	public @ResponseBody PRSMaintenanceReturn deletePurchaseRequest(@RequestParam int id) {
		Optional<PurchaseRequest> purchaseRequest = purchaseRequestRepository.findById(id);
		try {
			purchaseRequestRepository.delete(purchaseRequest.get());
			return PRSMaintenanceReturn.getMaintReturn(purchaseRequest.get());
		} catch (DataIntegrityViolationException dive) {
			return PRSMaintenanceReturn.getMaintReturnError(purchaseRequest, dive.getRootCause().toString());
		} catch (Exception e) {
			return PRSMaintenanceReturn.getMaintReturnError(purchaseRequest, e.toString());
		}
		
	}
	@PostMapping(path="/Change")
	public @ResponseBody PRSMaintenanceReturn updatePurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		try {
			purchaseRequestRepository.save(purchaseRequest);
			return PRSMaintenanceReturn.getMaintReturn(purchaseRequest);
		} catch (DataIntegrityViolationException dive) {
			return PRSMaintenanceReturn.getMaintReturnError(purchaseRequest, dive.getRootCause().toString());
		}catch (Exception e) {
			return PRSMaintenanceReturn.getMaintReturnError(purchaseRequest, e.toString());
		}
	}
}
