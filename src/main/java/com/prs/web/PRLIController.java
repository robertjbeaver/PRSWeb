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


import com.prs.web.BaseController;
import com.prs.business.purchaserequest.PRLI;
import com.prs.business.purchaserequest.PRLIRepository;
import com.prs.util.PRSMaintenanceReturn;

@Controller
@RequestMapping(path="/PRLI")
public class PRLIController extends BaseController {
	@Autowired
	private PRLIRepository prliRepository;

	@GetMapping(path="/List")
	public @ResponseBody Iterable<PRLI> getAllPRLI(){
		return prliRepository.findAll();
	}
	
	@GetMapping(path="/Get")
	public @ResponseBody List<PRLI> getPRLI(@RequestParam int id) {
		Optional<PRLI> p = prliRepository.findById(id);
		return getReturnArray(p);
	}
	@PostMapping(path="/Add")
	public @ResponseBody PRSMaintenanceReturn addNewPRLI (@RequestBody PRLI prli) {
		try {
			prliRepository.save(prli);
		}
		catch (Exception e) {
			prli = null;	
		}
		return PRSMaintenanceReturn.getMaintReturn(prli);
	}
	@GetMapping(path="/Remove")
	public @ResponseBody PRSMaintenanceReturn deletePRLI(@RequestParam int id) {
		Optional<PRLI> prli = prliRepository.findById(id);
		try {
			prliRepository.delete(prli.get());
			return PRSMaintenanceReturn.getMaintReturn(prli.get());
		} catch (DataIntegrityViolationException dive) {
			return PRSMaintenanceReturn.getMaintReturnError(prli, dive.getRootCause().toString());
		} catch (Exception e) {
			return PRSMaintenanceReturn.getMaintReturnError(prli, e.toString());
		}
		
	}
	@PostMapping(path="/Change")
	public @ResponseBody PRSMaintenanceReturn updatePRLI (@RequestBody PRLI prli) {
		try {
			prliRepository.save(prli);
			return PRSMaintenanceReturn.getMaintReturn(prli);
		} catch (DataIntegrityViolationException dive) {
			return PRSMaintenanceReturn.getMaintReturnError(prli, dive.getRootCause().toString());
		}catch (Exception e) {
			return PRSMaintenanceReturn.getMaintReturnError(prli, e.toString());
		}
	}
}