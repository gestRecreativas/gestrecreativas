package com.rms.recreativos.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rms.recreativos.entity.Contador;
import com.rms.recreativos.entity.Credito;
import com.rms.recreativos.entity.EmpresaGestion;
import com.rms.recreativos.entity.EmpresaMaquina;
import com.rms.recreativos.entity.Establecimiento;
import com.rms.recreativos.entity.Liquidacion;
import com.rms.recreativos.entity.Maquina;
import com.rms.recreativos.entity.Ticket;
import com.rms.recreativos.entity.TipoMaquina;
import com.rms.recreativos.entity.Usuario;
import com.rms.recreativos.service.AdminMaquinasBean;
import com.rms.recreativos.service.RecaudacionBean;
import com.rms.recreativos.util.DateUtil;
import com.rms.recreativos.util.ListUtil;

@Repository
@Transactional
public class RecreativosDAO {

	@Autowired
    private SessionFactory sessionFactory;
    
    private static final Logger logger = Logger.getLogger(RecreativosDAO.class);
    
    @SuppressWarnings("unchecked")
	public List<Establecimiento> getEstablecimientosTotal(Long idUsuario){
    	List<Establecimiento> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT DISTINCT EST.* FROM ESTABLECIMIENTO EST " + 
            		"INNER JOIN USUARIO USR ON EST.ID_EMPRESA_GESTION = USR.ID_EMPRESA " + 
            		"WHERE USR.ID_USUARIO = " + idUsuario + " " +
            		"ORDER BY EST.NOMBRE";
            logger.debug("getEstablecimientosUsuario: " +queryStr);
            session = sessionFactory.openSession();
            lista = (List<Establecimiento>) session.createSQLQuery(queryStr).addEntity(Establecimiento.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de establecimientos", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public List<Establecimiento> getEstablecimientosUsuario(Long idUsuario){
    	List<Establecimiento> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT DISTINCT EST.* FROM ESTABLECIMIENTO EST " + 
            					"INNER JOIN RUTA RUT ON RUT.ID_ESTABLECIMIENTO = EST.ID_ESTABLECIMIENTO " + 
            					"INNER JOIN USUARIO US ON US.ID_USUARIO = RUT.ID_USUARIO " + 
            					"WHERE US.ACTIVE = 1 AND US.ID_USUARIO = " + idUsuario + " " +
            					" AND US.ID_EMPRESA = EST.ID_EMPRESA_GESTION "+
            					"ORDER BY EST.NOMBRE";
            session = sessionFactory.openSession();
            logger.debug("getEstablecimientosUsuario: " +queryStr);
            lista = (List<Establecimiento>) session.createSQLQuery(queryStr).addEntity(Establecimiento.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de establecimientos", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public List<Establecimiento> getEstablecimientosAdmin(Long idUsuario){
    	List<Establecimiento> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT EST.* FROM ESTABLECIMIENTO EST " + 
            				  "INNER JOIN USUARIO USR ON EST.ID_EMPRESA_GESTION = USR.ID_EMPRESA " +            		 
            				  "WHERE  USR.ID_USUARIO = " + idUsuario + " AND USR.ID_EMPRESA = EST.ID_EMPRESA_GESTION  "+	
            				  "ORDER BY EST.NOMBRE";
            logger.debug("getEstablecimientosAdmin: " +queryStr);
            session = sessionFactory.openSession();
            lista = (List<Establecimiento>) session.createSQLQuery(queryStr).addEntity(Establecimiento.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de establecimientos", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public Usuario getUsuarioByLoginPassword(String login, String password){
    	List<Usuario> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM USUARIO " +
            		"WHERE ACTIVE = 1 " +
            		"AND LOGIN = BINARY '" + login + "' " +
            		"AND PASSWORD = BINARY '" + password + "'";
            session = sessionFactory.openSession();
            lista = (List<Usuario>) session.createSQLQuery(queryStr).addEntity(Usuario.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de usuario", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Usuario) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Establecimiento getEstablecimientoById(Long idEstablecimiento){
    	List<Establecimiento> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM ESTABLECIMIENTO " +
            		"WHERE ID_ESTABLECIMIENTO = " + idEstablecimiento;
            session = sessionFactory.openSession();
            lista = (List<Establecimiento>) session.createSQLQuery(queryStr).addEntity(Establecimiento.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de establecimiento", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Establecimiento) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public List<Maquina> getMaquinasByIdEstablecimiento(Long idEstablecimiento){
    	List<Maquina> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM MAQUINA " +
            		"WHERE ID_ESTABLECIMIENTO = " + idEstablecimiento;
            session = sessionFactory.openSession();
            lista = (List<Maquina>) session.createSQLQuery(queryStr).addEntity(Maquina.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de maquinas del establecimiento", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public Maquina getMaquinaById(Long idMaquina){
    	List<Maquina> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM MAQUINA " +
            		"WHERE ID_MAQUINA = " + idMaquina;
            session = sessionFactory.openSession();
            lista = (List<Maquina>) session.createSQLQuery(queryStr).addEntity(Maquina.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de maquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Maquina) lista.get(0);
        }
        return null;
    }
    
    private synchronized Long getNextIdContador(){
		Session session = null;
		int nextId = 0;
		try{
			String queryStr = "SELECT MAX(ID_CONTADOR) FROM CONTADOR";
			session = sessionFactory.openSession();
			Integer maxId = (Integer) session.createSQLQuery(queryStr).uniqueResult(); 
			if (maxId == null){
				nextId++;
			} else {
				nextId = maxId.intValue();
				nextId++;
			}
		} catch (Exception e){
			logger.error("Error obteniendo el nextIdContador", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return new Long(nextId);
	}
    
    public synchronized boolean createContador(Contador contador){
        Session session = null;
        boolean guardado = true;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            contador.setIdContador(getNextIdContador());
            session.save(contador);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el save del contador", e);
            e.printStackTrace();
            guardado = false;
        } finally{
            if (session != null){
                session.close();
            }
        }
        return guardado;
    }
    
    public synchronized boolean updateContador(Contador contador){
        Session session = null;
        boolean guardado = true;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(contador);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el update del contador", e);
            e.printStackTrace();
            guardado = false;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return guardado;
    }
    
    @SuppressWarnings("unchecked")
	public Contador getContador(Long idMaquina, Date fecha){
    	List<Contador> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CONTADOR " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND FECHA = '" + DateUtil.getStringFromDate(fecha, "yyyy-MM-dd") + "'";
            session = sessionFactory.openSession();
            lista = (List<Contador>) session.createSQLQuery(queryStr).addEntity(Contador.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de contador", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Contador) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Contador getLastContador(Long idMaquina){
    	List<Contador> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CONTADOR " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND FECHA < '" + DateUtil.getStringFromDate(DateUtil.getNow(), "yyyy-MM-dd") + "' " +
            		"ORDER BY FECHA DESC";
            session = sessionFactory.openSession();
            lista = (List<Contador>) session.createSQLQuery(queryStr).addEntity(Contador.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de contador", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Contador) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Contador getContadorActual(Long idMaquina){
    	List<Contador> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CONTADOR " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND FECHA = '" + DateUtil.getStringFromDate(DateUtil.getNow(), "yyyy-MM-dd") + "'";
            session = sessionFactory.openSession();
            lista = (List<Contador>) session.createSQLQuery(queryStr).addEntity(Contador.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de contador", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Contador) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Contador getContadorAnterior(Long idMaquina, Date fecha){
    	List<Contador> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CONTADOR " +
            		"WHERE ID_MAQUINA = "+ idMaquina + " " +
            		"AND FECHA < '" + DateUtil.getStringFromDate(fecha, "yyyy-MM-dd") + "' " +
            		"ORDER BY FECHA DESC LIMIT 1";
            session = sessionFactory.openSession();
            lista = (List<Contador>) session.createSQLQuery(queryStr).addEntity(Contador.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de contador anterior", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Contador) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public TipoMaquina getTipoMaquina(Long idMaquina){
    	List<TipoMaquina> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT t.* FROM MAQUINA m, TIPO_MAQUINA t " +
            		"WHERE m.ID_MAQUINA = " + idMaquina + " " +
            		"AND m.ID_TIPO_MAQUINA = t.ID_TIPO_MAQUINA";
            session = sessionFactory.openSession();
            lista = (List<TipoMaquina>) session.createSQLQuery(queryStr).addEntity(TipoMaquina.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda del tipo de maquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (TipoMaquina) lista.get(0);
        }
        return null;
    }
    
    private synchronized Long getNextIdLiquidacion(){
		Session session = null;
		int nextId = 0;
		try{
			String queryStr = "SELECT MAX(ID_LIQUIDACION) FROM LIQUIDACION";
			session = sessionFactory.openSession();
			Integer maxId = (Integer) session.createSQLQuery(queryStr).uniqueResult(); 
			if (maxId == null){
				nextId++;
			} else {
				nextId = maxId.intValue();
				nextId++;
			}
		} catch (Exception e){
			logger.error("Error obteniendo el nextIdLiquidacion", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return new Long(nextId);
	}
    
    public synchronized Liquidacion createLiquidacion(Liquidacion liquidacion){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            liquidacion.setIdLiquidacion(getNextIdLiquidacion());
            session.save(liquidacion);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el save del liquidacion", e);
            e.printStackTrace();
            return null;
        } finally{
            if (session != null){
                session.close();
            }
        }
        return liquidacion;
    }
    
    public synchronized Liquidacion updateLiquidacion(Liquidacion liquidacion){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(liquidacion);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el update del liquidacion", e);
            e.printStackTrace();
            return null;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return liquidacion;
    }
    
    @SuppressWarnings("unchecked")
	public Liquidacion getLiquidacionActual(Long idMaquina){
    	List<Liquidacion> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM LIQUIDACION " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND FECHA = '" + DateUtil.getStringFromDate(DateUtil.getNow(), "yyyy-MM-dd") + "'";
            session = sessionFactory.openSession();
            lista = (List<Liquidacion>) session.createSQLQuery(queryStr).addEntity(Liquidacion.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de Liquidacion", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Liquidacion) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public BigDecimal getRetencionAcumuladaAnio(Long idMaquina){
		Session session = null;
		BigDecimal acumulado = null;
		try{
			String anioActual = DateUtil.getStringFromDate(DateUtil.getNow(), "yyyy");
			String queryStr = "SELECT IFNULL(SUM(RETENCION),0) FROM LIQUIDACION " + 
					"WHERE ID_MAQUINA = " + idMaquina + " " +
					"AND FECHA >= '" + anioActual + "-01-01' " +
					"AND FECHA <= '" + anioActual + "-12-31'";
			session = sessionFactory.openSession();
			acumulado = BigDecimal.valueOf(((Double) session.createSQLQuery(queryStr).uniqueResult()).doubleValue()).setScale(2);
		} catch (Exception e){
			logger.error("Error obteniendo la retencion acumulada del anio", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return acumulado;
	}
    
    private synchronized Long getNextIdCredito(){
		Session session = null;
		int nextId = 0;
		try{
			String queryStr = "SELECT MAX(ID_CREDITO) FROM CREDITO";
			session = sessionFactory.openSession();
			Integer maxId = (Integer) session.createSQLQuery(queryStr).uniqueResult(); 
			if (maxId == null){
				nextId++;
			} else {
				nextId = maxId.intValue();
				nextId++;
			}
		} catch (Exception e){
			logger.error("Error obteniendo el nextIdCredito", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return new Long(nextId);
	}
    
    public synchronized Credito createCredito(Credito credito){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            credito.setIdCredito(getNextIdCredito());
            session.save(credito);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el save del credito", e);
            e.printStackTrace();
            return null;
        } finally{
            if (session != null){
                session.close();
            }
        }
        return credito;
    }
    
    public synchronized Credito updateCredito(Credito credito){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(credito);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el update del credito", e);
            e.printStackTrace();
            return null;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return credito;
    }
    
    @SuppressWarnings("unchecked")
    public Credito getCreditoPendiente(Long idEstablecimiento){
    	List<Credito> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CREDITO " +
            		"WHERE ID_ESTABLECIMIENTO = " + idEstablecimiento + " " +
            		"AND  ( IMPORTE_PENDIENTE > 0  OR (FECHACOBRO = '" + DateUtil.getStringFromDate(DateUtil.getNow(), "yyyy-MM-dd") + "' AND CREDITOINICIALACTUAL > 0  ))" +
            		"ORDER BY FECHA DESC";
            logger.debug("getCreditoPendiente: " +queryStr);
            session = sessionFactory.openSession();
            lista = (List<Credito>) session.createSQLQuery(queryStr).addEntity(Credito.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de credito", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Credito) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public Credito getCreditoById(Long idCredito){
    	List<Credito> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CREDITO " +
            		"WHERE ID_CREDITO = " + idCredito;
            session = sessionFactory.openSession();
            lista = (List<Credito>) session.createSQLQuery(queryStr).addEntity(Credito.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de credito", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Credito) lista.get(0);
        }
        return null;
    }
    
    private synchronized Long getNextIdTicket(){
		Session session = null;
		int nextId = 0;
		try{
			String queryStr = "SELECT MAX(ID_TICKET) FROM TICKET";
			session = sessionFactory.openSession();
			Integer maxId = (Integer) session.createSQLQuery(queryStr).uniqueResult(); 
			if (maxId == null){
				nextId++;
			} else {
				nextId = maxId.intValue();
				nextId++;
			}
		} catch (Exception e){
			logger.error("Error obteniendo el nextIdTicket", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return new Long(nextId);
	}
    
    public synchronized Ticket createTicket(Ticket ticket){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            ticket.setIdTicket(getNextIdTicket());
            session.save(ticket);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el save del ticket", e);
            e.printStackTrace();
            return null;
        } finally{
            if (session != null){
                session.close();
            }
        }
        return ticket;
    }
    
    public synchronized Ticket updateTicket(Ticket ticket){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(ticket);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el update del ticket", e);
            e.printStackTrace();
            return null;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return ticket;
    }
    
    private synchronized Long getNextIdMaquina(){
		Session session = null;
		int nextId = 0;
		try{
			String queryStr = "SELECT MAX(ID_MAQUINA) FROM MAQUINA";
			session = sessionFactory.openSession();
			Integer maxId = (Integer) session.createSQLQuery(queryStr).uniqueResult(); 
			if (maxId == null){
				nextId++;
			} else {
				nextId = maxId.intValue();
				nextId++;
			}
		} catch (Exception e){
			logger.error("Error obteniendo el nextIdMaquina", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return new Long(nextId);
	}
    
    public synchronized boolean createMaquina(Maquina maquina){
        Session session = null;
        boolean guardado = true;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            maquina.setIdMaquina(getNextIdMaquina());
            session.save(maquina);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el save del maquina", e);
            e.printStackTrace();
            guardado = false;
        } finally{
            if (session != null){
                session.close();
            }
        }
        return guardado;
    }
    
    public synchronized boolean updateMaquina(Maquina maquina){
        Session session = null;
        boolean guardado = true;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(maquina);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el update del maquina", e);
            e.printStackTrace();
            guardado = false;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return guardado;
    }
    
    private synchronized Long getNextIdEstablecimiento(){
		Session session = null;
		int nextId = 0;
		try{
			String queryStr = "SELECT MAX(ID_ESTABLECIMIENTO) FROM ESTABLECIMIENTO";
			session = sessionFactory.openSession();
			Integer maxId = (Integer) session.createSQLQuery(queryStr).uniqueResult(); 
			if (maxId == null){
				nextId++;
			} else {
				nextId = maxId.intValue();
				nextId++;
			}
		} catch (Exception e){
			logger.error("Error obteniendo el nextIdEstablecimiento", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return new Long(nextId);
	}
    
    public synchronized boolean createEstablecimiento(Establecimiento establecimiento){
        Session session = null;
        boolean guardado = true;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            establecimiento.setIdEstablecimiento(getNextIdEstablecimiento());
            session.save(establecimiento);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el save del establecimiento", e);
            e.printStackTrace();
            guardado = false;
        } finally{
            if (session != null){
                session.close();
            }
        }
        return guardado;
    }
    
    public synchronized boolean updateEstablecimiento(Establecimiento establecimiento){
        Session session = null;
        boolean guardado = true;
        try{
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(establecimiento);
            tx.commit();
        } catch (Exception e){
        	logger.error("Error al hacer el update del establecimiento", e);
            e.printStackTrace();
            guardado = false;
        } finally {
            if (session != null){
                session.close();
            }
        }
        return guardado;
    }
    
    @SuppressWarnings("unchecked")
    public Ticket getTicketActual(Long idEstablecimiento){
    	List<Ticket> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM TICKET " +
            		"WHERE ID_ESTABLECIMIENTO = " + idEstablecimiento + " " +
            		"AND FECHA = '" + DateUtil.getStringFromDate(DateUtil.getNow(), "yyyy-MM-dd") + "'";
            session = sessionFactory.openSession();
            lista = (List<Ticket>) session.createSQLQuery(queryStr).addEntity(Ticket.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de ticket", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Ticket) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public Ticket getTicketById(Long idTicket){
    	List<Ticket> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM TICKET " +
            		"WHERE ID_TICKET = " + idTicket;
            session = sessionFactory.openSession();
            lista = (List<Ticket>) session.createSQLQuery(queryStr).addEntity(Ticket.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de ticket", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Ticket) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Contador getContadorByTicketAndMaquina(Long idTicket, Long idMaquina){
    	List<Contador> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CONTADOR " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND ID_TICKET = " + idTicket;
            session = sessionFactory.openSession();
            lista = (List<Contador>) session.createSQLQuery(queryStr).addEntity(Contador.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda del contador del ticket y maquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Contador) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Contador getContadorByFechaAndMaquina(Date fecha, Long idMaquina){
    	List<Contador> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM CONTADOR " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND FECHA = '" + DateUtil.getStringFromDate(fecha, "yyyy-MM-dd") + "'";
            session = sessionFactory.openSession();
            lista = (List<Contador>) session.createSQLQuery(queryStr).addEntity(Contador.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda del contador de la fecha y maquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Contador) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Liquidacion getLiquidacionByTicketAndMaquina(Long idTicket, Long idMaquina){
    	List<Liquidacion> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM LIQUIDACION " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND ID_TICKET = " + idTicket;
            session = sessionFactory.openSession();
            lista = (List<Liquidacion>) session.createSQLQuery(queryStr).addEntity(Liquidacion.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de la liquidacion del ticket y maquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Liquidacion) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public Liquidacion getLiquidacionByFechaAndMaquina(Date fecha, Long idMaquina){
    	List<Liquidacion> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM LIQUIDACION " +
            		"WHERE ID_MAQUINA = " + idMaquina + " " +
            		"AND FECHA = '" + DateUtil.getStringFromDate(fecha, "yyyy-MM-dd") + "'";
            session = sessionFactory.openSession();
            lista = (List<Liquidacion>) session.createSQLQuery(queryStr).addEntity(Liquidacion.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de la liquidacion de la fecha y maquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (Liquidacion) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public EmpresaGestion getEmpresaGestionByIdUsuario(Long idUsuario){
    	List<EmpresaGestion> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM EMPRESA_GESTION " +
            		"WHERE ID_EMPRESA_GESTION IN (" +
            		"SELECT ID_EMPRESA FROM USUARIO " +
            		"WHERE ID_USUARIO = " + idUsuario + ")";
            session = sessionFactory.openSession();
            lista = (List<EmpresaGestion>) session.createSQLQuery(queryStr).addEntity(EmpresaGestion.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de la empresa gestion", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (EmpresaGestion) lista.get(0);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public EmpresaMaquina getEmpresaMaquinaById(Long idEmpresaMaquina){
    	List<EmpresaMaquina> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM EMPRESA_MAQUINA " +
            		"WHERE ID_EMPRESA_MAQUINA = " + idEmpresaMaquina;
            session = sessionFactory.openSession();
            lista = (List<EmpresaMaquina>) session.createSQLQuery(queryStr).addEntity(EmpresaMaquina.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de la empresa maquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        if (!ListUtil.nullOrEmptyList(lista)){
        	return (EmpresaMaquina) lista.get(0);
        }
        return null;
    }
    
    public void updateTicketContadores(Long idTicket, List<Contador> contadores){
    	Session session = null;
    	try{
    		String ids = "";
    		Iterator<Contador> itContadores = contadores.iterator();
    		Contador contador = null;
    		boolean primero = true;
    		while (itContadores.hasNext()){
    			contador = itContadores.next();
    			if (primero){
    				ids += contador.getIdContador().toString();
    				primero = false;
    			} else {
    				ids += ", " + contador.getIdContador().toString();
    			}
    		}
			String queryStr = "UPDATE CONTADOR SET ID_TICKET = " + idTicket + " " +
					"WHERE ID_CONTADOR IN (" + ids + ")";
			logger.debug("Query update: " + queryStr);
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			int result = session.createSQLQuery(queryStr).executeUpdate();
			tx.commit();
		} catch (Exception e){
			logger.error("Error realizando actualizacion idTicket en contadores", e);
            e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
    }
    
    public void updateTicketLiquidaciones(Long idTicket, List<Liquidacion> liquidaciones){
    	Session session = null;
    	try{
    		String ids = "";
    		Iterator<Liquidacion> itLiquidaciones = liquidaciones.iterator();
    		Liquidacion liquidacion = null;
    		boolean primero = true;
    		while (itLiquidaciones.hasNext()){
    			liquidacion = itLiquidaciones.next();
    			if (primero){
    				ids += liquidacion.getIdLiquidacion().toString();
    				primero = false;
    			} else {
    				ids += ", " + liquidacion.getIdLiquidacion().toString();
    			}
    		}
			String queryStr = "UPDATE LIQUIDACION SET ID_TICKET = " + idTicket + " " +
					"WHERE ID_LIQUIDACION IN (" + ids + ")";
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			int result = session.createSQLQuery(queryStr).executeUpdate();
			tx.commit();
		} catch (Exception e){
			logger.error("Error realizando actualizacion idTicket en liquidaciones", e);
            e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
    }
    
    @SuppressWarnings("unchecked")
    public List<Ticket> getTicketsHistorico(Long idEstablecimiento, Long idUsuario, Date fechaInicio, Date fechaFin){
    	List<Ticket> lista = null;
        Session session = null;
        try{
            /*String queryStr = "SELECT DISTINCT TKT.* FROM TICKET TKT " + 
            		"INNER JOIN ESTABLECIMIENTO EST ON TKT.ID_ESTABLECIMIENTO = EST.ID_ESTABLECIMIENTO " + 
            		"INNER JOIN MAQUINA MAQ ON EST.ID_ESTABLECIMIENTO = MAQ.ID_ESTABLECIMIENTO " + 
            		"INNER JOIN EMPRESA_GESTION EMP ON MAQ.ID_EMPRESA_GESTION = EMP.ID_EMPRESA_GESTION " + 
            		"INNER JOIN USUARIO USR ON EMP.ID_EMPRESA_GESTION = USR.ID_EMPRESA " + 
            		"WHERE TKT.ID_ESTABLECIMIENTO = " + idEstablecimiento + " " +
            		"AND USR.ID_USUARIO = " + idUsuario + " ";*/
            String queryStr = "SELECT DISTINCT TKT.* FROM TICKET TKT " + 
            		"INNER JOIN ESTABLECIMIENTO EST ON TKT.ID_ESTABLECIMIENTO = EST.ID_ESTABLECIMIENTO " + 
            		"WHERE TKT.ID_ESTABLECIMIENTO = " + idEstablecimiento + " " ;
            if (fechaInicio != null){
            	queryStr += "AND TKT.FECHA >= '" + DateUtil.getStringFromDate(fechaInicio, "yyyy-MM-dd") + "' ";
            }
            if (fechaFin != null){
            	queryStr += "AND TKT.FECHA <= '" + DateUtil.getStringFromDate(fechaFin, "yyyy-MM-dd") + "' ";
            }
            queryStr += "ORDER BY FECHA DESC LIMIT 56";
            session = sessionFactory.openSession();
            logger.debug("getTicketsHistorico: " + queryStr);
            lista = (List<Ticket>) session.createSQLQuery(queryStr).addEntity(Ticket.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de ticket", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    public boolean deleteTicket(Long idTicket){
    	boolean resultado = true;
    	Session session = null;
    	try{
			String queryStr = "DELETE FROM TICKET WHERE ID_TICKET = " + idTicket;
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			int result = session.createSQLQuery(queryStr).executeUpdate();
			tx.commit();
		} catch (Exception e){
			resultado = false;
			logger.error("Error borrando el ticket", e);
            e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
    	return resultado;
    }
    
    public boolean deleteContador(Long idContador){
    	boolean resultado = true;
    	Session session = null;
    	try{
			String queryStr = "DELETE FROM CONTADOR WHERE ID_CONTADOR = " + idContador;
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			int result = session.createSQLQuery(queryStr).executeUpdate();
			tx.commit();
		} catch (Exception e){
			resultado = false;
			logger.error("Error borrando el contador", e);
            e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
    	return resultado;
    }
    
    public boolean deleteLiquidacion(Long idLiquidacion){
    	boolean resultado = true;
    	Session session = null;
    	try{
			String queryStr = "DELETE FROM LIQUIDACION WHERE ID_LIQUIDACION = " + idLiquidacion;
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			int result = session.createSQLQuery(queryStr).executeUpdate();
			tx.commit();
		} catch (Exception e){
			resultado = false;
			logger.error("Error borrando el liquidacion", e);
            e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
    	return resultado;
    }
    
    @SuppressWarnings("unchecked")
    public List<RecaudacionBean> getRecaudaciones(Long idUsuario, Date fecha){
    	List<RecaudacionBean> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT EM.RAZON_SOCIAL AS empresaMaquina, " +
            		"E.NOMBRE AS establecimiento, E.ID_ESTABLECIMIENTO AS idEstablecimiento, " +
            		"T.FECHA AS fecha, U.LOGIN AS login, " +
            		"T.ID_TICKET AS idTicket ," +
            		"T.HORA AS hora " +
            		"FROM TICKET T " + 
            		"INNER JOIN EMPRESA_MAQUINA EM ON T.ID_EMPRESA_MAQUINA = EM.ID_EMPRESA_MAQUINA " +
            		"INNER JOIN ESTABLECIMIENTO E ON T.ID_ESTABLECIMIENTO = E.ID_ESTABLECIMIENTO " +
            		"INNER JOIN USUARIO U ON T.ID_USUARIO = U.ID_USUARIO " +
            		"INNER JOIN USUARIO U2 ON U2.ID_USUARIO = " + idUsuario + " " +
            		"WHERE U.ID_EMPRESA = U2.ID_EMPRESA " +
            		"AND T.FECHA = '" + DateUtil.getStringFromDate(fecha, "yyyy-MM-dd") + "' " +
            		"ORDER BY EM.RAZON_SOCIAL";
            session = sessionFactory.openSession();
            lista = (List<RecaudacionBean>) session.createSQLQuery(queryStr)
            							.addScalar("empresaMaquina", StringType.INSTANCE)
            							.addScalar("establecimiento", StringType.INSTANCE)
            							.addScalar("idEstablecimiento", LongType.INSTANCE)
            							.addScalar("fecha", DateType.INSTANCE)
            							.addScalar("login", StringType.INSTANCE)
            							.addScalar("idTicket", LongType.INSTANCE)
            							.addScalar("hora", StringType.INSTANCE)
            							.setResultTransformer(Transformers.aliasToBean(RecaudacionBean.class)).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de recaudaciones", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public List<AdminMaquinasBean> getMaquinasTotal(Long idUsuario){
    	List<AdminMaquinasBean> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT DISTINCT MAQ.ID_MAQUINA AS idMaquina, MAQ.NOMBRE AS nombre, " +
            		"EST.NOMBRE AS establecimiento " +
            		"FROM MAQUINA MAQ " +
            		"INNER JOIN ESTABLECIMIENTO EST ON MAQ.ID_ESTABLECIMIENTO = EST.ID_ESTABLECIMIENTO " +
            		"INNER JOIN EMPRESA_GESTION EMP ON MAQ.ID_EMPRESA_GESTION = EMP.ID_EMPRESA_GESTION " + 
            		"INNER JOIN USUARIO USR ON EMP.ID_EMPRESA_GESTION = USR.ID_EMPRESA " + 
            		"WHERE USR.ID_USUARIO = " + idUsuario + " " +
            		"ORDER BY MAQ.NOMBRE";
            session = sessionFactory.openSession();
            lista = (List<AdminMaquinasBean>) session.createSQLQuery(queryStr)
					.addScalar("idMaquina", LongType.INSTANCE)
					.addScalar("nombre", StringType.INSTANCE)
					.addScalar("establecimiento", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(AdminMaquinasBean.class)).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de maquinas", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public List<TipoMaquina> getTiposMaquina(){
    	List<TipoMaquina> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM TIPO_MAQUINA " +
            		"ORDER BY ID_TIPO_MAQUINA";
            session = sessionFactory.openSession();
            lista = (List<TipoMaquina>) session.createSQLQuery(queryStr).addEntity(TipoMaquina.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de tiposMaquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
	public List<EmpresaMaquina> getEmpresasMaquina(){
    	List<EmpresaMaquina> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM EMPRESA_MAQUINA " +
            		"ORDER BY ID_EMPRESA_MAQUINA";
            session = sessionFactory.openSession();
            lista = (List<EmpresaMaquina>) session.createSQLQuery(queryStr).addEntity(EmpresaMaquina.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de empresaMaquina", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
    public List<Establecimiento> getEstablecimientosByIdEmpresaMaquina(Long idEmpresaMaquina){
    	List<Establecimiento> lista = null;
        Session session = null;
        try{
            String queryStr = "SELECT * FROM ESTABLECIMIENTO " + 
            		"WHERE ID_EMPRESA_MAQUINA = " + idEmpresaMaquina + " " +
            		"ORDER BY NOMBRE";
            session = sessionFactory.openSession();
            lista = (List<Establecimiento>) session.createSQLQuery(queryStr).addEntity(Establecimiento.class).list();
        }catch (Exception e){
            logger.error("Error realizando la busqueda de establecimientos", e);
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return lista;
    }
    
    public boolean getTieneMaquinas(Long idEstablecimiento) {
    	int numMaquinas = 0;
		Session session = null;
		try{
			String queryStr = "SELECT COUNT(*) FROM MAQUINA " +
					"WHERE ID_ESTABLECIMIENTO = " + idEstablecimiento;
			session = sessionFactory.openSession();
			numMaquinas = ((BigInteger) session.createSQLQuery(queryStr).uniqueResult()).intValue(); 
		} catch (Exception e){
			logger.error("Error realizando en conteo de maquinas del establecimiento", e);
			e.printStackTrace();
		} finally {
			if (session != null){
				session.close();
			}
		}
		return numMaquinas > 0;
    }

    @SuppressWarnings("unchecked")
	public Usuario getUsuarioById(Long idUsuario) {		
			 	List<Usuario> lista = null;
		        Session session = null;
		        try{
		            String queryStr = "SELECT * FROM USUARIO " +
		            		"WHERE ACTIVE = 1 " +
		            		"AND ID_USUARIO = '" + idUsuario + "' " ;
		            session = sessionFactory.openSession();
		            lista = (List<Usuario>) session.createSQLQuery(queryStr).addEntity(Usuario.class).list();
		        }catch (Exception e){
		            logger.error("Error realizando la busqueda de usuario por id ", e);
		            e.printStackTrace();
		        }finally {
		            if (session != null){
		                session.close();
		            }
		        }
		        if (!ListUtil.nullOrEmptyList(lista)){
		        	return (Usuario) lista.get(0);
		        }
		        return null;
		  
	}
}