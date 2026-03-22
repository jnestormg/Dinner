-- Limpiar datos previos (DELETE es más seguro que TRUNCATE para resetear IDENTITY)
--DELETE FROM menus;
--DELETE FROM restaurantes;

INSERT INTO restaurantes (
    nombre,
    telefono,
    correo,
    calle,
    numero,
    colonia,
    ciudad,
    estado,
    codigo_postal
) VALUES (
    'Restaurante La Parrilla',
    2221234567,
    'contacto@parrilla.com',
    'Av. Juarez',
    123,
    'Centro',
    'Puebla',
    'Puebla',
    72000
);

INSERT INTO restaurantes (
    nombre, telefono, correo,
    calle, numero, colonia, ciudad, estado, codigo_postal
) VALUES (
    'Sushi House',
    5512345678,
    'info@sushihouse.com',
    'Calle Reforma',
    45,
    'Roma Norte',
    'CDMX',
    'Ciudad de Mexico',
    6700
);

INSERT INTO restaurantes (
    nombre, telefono, correo,
    calle, numero, colonia, ciudad, estado, codigo_postal
) VALUES (
    'Tacos El Paisa',
    3339876543,
    'ventas@elpaisa.mx',
    'Av. Vallarta',
    890,
    'Americana',
    'Guadalajara',
    'Jalisco',
    44160
);

INSERT INTO restaurantes (
    nombre, telefono, correo,
    calle, numero, colonia, ciudad, estado, codigo_postal
) VALUES (
    'Mariscos del Puerto',
    2294567890,
    'contacto@mariscospuerto.com',
    'Malecon Costero',
    12,
    'Centro',
    'Veracruz',
    'Veracruz',
    91700
);

INSERT INTO restaurantes (
    nombre, telefono, correo,
    calle, numero, colonia, ciudad, estado, codigo_postal
) VALUES (
    'Pizza Italiana',
    8187654321,
    'reservas@pizzaitaliana.com',
    'Av. Constitucion',
    300,
    'Obispado',
    'Monterrey',
    'Nuevo Leon',
    64060
);


---menus
INSERT INTO menus (
    nombre,
    descripcion,
    restaurante_id
) VALUES (
    'Menu Ejecutivo',
    'Incluye sopa, plato fuerte y bebida',
    (SELECT id FROM restaurantes WHERE nombre = 'Restaurante La Parrilla')
);

INSERT INTO menus (
    nombre,
    descripcion,
    restaurante_id
) VALUES (
    'Menu Sushi Clasico',
    'Variedad de rollos tradicionales',
    (SELECT id FROM restaurantes WHERE nombre = 'Sushi House')
);

INSERT INTO menus (
    nombre,
    descripcion,
    restaurante_id
) VALUES (
    'Combo Tacos',
    'Orden de 5 tacos con refresco',
    (SELECT id FROM restaurantes WHERE nombre = 'Tacos El Paisa')
);

INSERT INTO menus (
    nombre,
    descripcion,
    restaurante_id
) VALUES (
    'Menu Mariscos Premium',
    'Camarones, pulpo y pescado fresco',
    (SELECT id FROM restaurantes WHERE nombre = 'Mariscos del Puerto')
);

INSERT INTO menus (
    nombre,
    descripcion,
    restaurante_id
) VALUES (
    'Menu Pizza Familiar',
    'Pizza grande + refresco 2L',
    (SELECT id FROM restaurantes WHERE nombre = 'Pizza Italiana')
);
