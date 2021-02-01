package bo.gob.sin.sre.gpri.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bo.gob.sin.sre.gpri.client.IEjemploClient;

@Service
public class EjemploClientImpl implements IEjemploClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(EjemploClientImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public String dummy(Integer pParam) {
		return "";
	}
}
