package servicios;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Validation3Days {
    private static final String USOS_SERVICIO_FILE = "usos_servicio.json";
    private Map<String, Map<String, Integer>> usosServicio = new HashMap<>();

    public void validarUsoServicio(String usuario) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
        String fecha = dateFormat.format(fechaActual);

        cargarUsosServicio();

        if (!usosServicio.containsKey(usuario)) {
            usosServicio.put(usuario, new HashMap<>());
        }
        Map<String, Integer> usosPorDia = usosServicio.get(usuario);
        if (!usosPorDia.containsKey(fecha)) {
            usosPorDia.put(fecha, 0);
        }
        int contador = usosPorDia.get(fecha);
        if (contador >= 3) {
            throw new RuntimeException("Ha excedido el número de usos por día");
        }
        usosPorDia.put(fecha, contador + 1);

        guardarUsosServicio();

        System.out.println("Validando uso del servicio para el usuario " + usuario);
    }

    private void cargarUsosServicio() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(USOS_SERVICIO_FILE);
        if (file.exists()) {
            try {
                usosServicio = mapper.readValue(file, new TypeReference<Map<String, Map<String, Integer>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void guardarUsosServicio() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(USOS_SERVICIO_FILE);
        try {
            mapper.writeValue(file, usosServicio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
