package dh.backend.service;

import dh.backend.dao.IDao;
import dh.backend.model.Avion;

import java.util.List;

public class AvionService {

    private IDao<Avion> avionesIDao;

    public AvionService(IDao<Avion> avionesIDao) {
        this.avionesIDao = avionesIDao;
    }

    public IDao<Avion> getAvionesIDao() {
        return avionesIDao;
    }

    public void setAvionesIDao(IDao<Avion> avionesIDao) {
        this.avionesIDao = avionesIDao;
    }

    public Avion registrarAvion(Avion avion){
        return avionesIDao.registrar(avion);
    }

    public Avion buscarPorId(Integer id){
        return avionesIDao.buscarPorId(id);
    }

    public void eliminarPorId(Integer id){
        avionesIDao.eliminarPorId(id);
    }

    public List<Avion> buscarTodos(){
        return avionesIDao.buscarTodos();
    }



}
