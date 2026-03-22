package com.diner.diner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = DinerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class RestauranteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "/scripts/test-data.sql")
    public void testObtenerRestaurantes() throws Exception {
        mockMvc.perform(get("/api/restaurantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // $ es un objeto (Page)
                .andExpect(jsonPath("$").isMap())

                // El array real está en content
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))

                // Validar un campo del primer restaurante
                .andExpect(jsonPath("$.content[0].nombre")
                        .value("Mariscos del Puerto"));
    }


      /* Prueba para crear un nuevo restaurante */
  @Test
  @Sql(scripts = "/scripts/test-data.sql")
  public void testCrearRestaurante() throws Exception {
    String nuevoRestaurante = """
        {
  "nombre": "Taquería El Paisano",
  "telefono": 22212345669,
  "correo": "contacto@gmail.com",
  "direccion": {
    "calle": "Av. Juárez",
    "ciudad": "Puebla"
  },
  "menus": []
}

                """;

    mockMvc.perform(post("/api/restaurantes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(nuevoRestaurante))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nombre").value("Taquería El Paisano"))
        .andExpect(jsonPath("$.telefono").value("22212345669"));

  }

    /**
   * prueba para eliminar un restaurante
   */
  @Test
  @Sql(scripts = "/scripts/test-data.sql")
  public void testEliminarRestaurante() throws Exception {
    // Eliminar el restaurante con ID 1
    mockMvc.perform(delete("/api/restaurantes/24"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.mensaje").value("Restaurante eliminado exitosamente"));
    // Verificar que el restaurante ya no existe
    mockMvc.perform(get("/api/restaurantes/1"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.mensaje").value("No se encontró un restaurante con el ID proporcionado"));
  }


  /**
   * prueba para actualizar un restaurante
   */
  @Test
  @Sql(scripts = "/scripts/test-data.sql")
  public void testActualizarRestaurante() throws Exception {
    String restauranteActualizado = """
        {
          "nombre": "Restaurante Actualizado",
          "telefono": "0987654321",
          "email": "restaurante@actualizado.com",
          "direccion": {
            "calle": "Calle Actualizada 456",
            "ciudad": "Ciudad Y",
            "estado": "Estado Y",
            "codigoPostal": "67890",
            "pais": "País Y"
          }
        }
        """;
    // Actualizar el restaurante con ID 2
    mockMvc.perform(put("/api/restaurantes/2")
        .contentType(MediaType.APPLICATION_JSON)
        .content(restauranteActualizado))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("Restaurante Actualizado"))
        .andExpect(jsonPath("$.telefono").value("0987654321"));
    // Verificar que los cambios se hayan guardado
    mockMvc.perform(get("/api/restaurantes/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("Restaurante Actualizado"))
        .andExpect(jsonPath("$.telefono").value("0987654321"));
  }

    // prueba para buscar restaurantes por nombre y email
  @Test
  @Sql(scripts = "/scripts/test-data.sql")
  public void testBuscarRestaurantesPorNombreYEmail() throws Exception {
    mockMvc.perform(get("/api/restaurantes/buscar2")
        .param("nombre", "Delicias")
        .param("email", "mar@correo.com"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].nombre").value("Delicias del Mar"))
        .andExpect(jsonPath("$[0].email").value("mar@correo.com"));
  }


}
