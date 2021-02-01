package bo.gob.sin.sre.gpri.query.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.gpri.query.IEjemploQuery;

@Service
public class EjemploQueryImpl implements IEjemploQuery {
	
	private static final Logger LOG = LoggerFactory.getLogger(EjemploQueryImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String dummy(Integer pParam) {
		return "";
	}
}
