/*
===============================================================================
 DeliveryRice - Initial Demo Data
===============================================================================

Este archivo carga datos iniciales para facilitar las pruebas de la API.

Credenciales de acceso:

ADMIN
email: admin@deliveryrice.com
password: Admin1234

CLIENT
email: cliente@deliveryrice.com
password: Admin1234

Las contraseñas se almacenan utilizando BCrypt.

Se utiliza INSERT IGNORE para que el script pueda ejecutarse varias veces sin
provocar errores por claves duplicadas.

===============================================================================
*/

-- ============================================================================
-- USERS
-- ============================================================================

INSERT IGNORE INTO users
(
    mail,
    password,
    name,
    lastname,
    phone,
    created_at,
    role
)
VALUES
(
    'admin@deliveryrice.com',
    '$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC',
    'Admin',
    'DeliveryRice',
    '600000000',
    NOW(),
    'ADMIN'
);

INSERT IGNORE INTO users
(
    mail,
    password,
    name,
    lastname,
    phone,
    created_at,
    role
)
VALUES
(
    'cliente@deliveryrice.com',
    '$2a$10$LynYUKHm6okgxNnoPW/FF..r1tjN57HjSlKega3RKcFTSiyvh05GC',
    'Cliente',
    'Demo',
    '611111111',
    NOW(),
    'CLIENT'
);

-- ============================================================================
-- PRODUCTS
-- ============================================================================

INSERT IGNORE INTO products
(
    name,
    description,
    price,
    stock,
    category,
    available,
    created_at,
    image_url
)
VALUES

-- ============================================================================
-- RICE
-- ============================================================================

(
'Arroz del Señorito',
'Arroz seco de marisco limpio, intenso y tradicional.',
16.90,
30,
'RICE',
true,
NOW(),
'https://jetextramar.com/wp-content/uploads/2020/07/arrozalsenorito_web.jpg'
),

(
'Paella Valenciana',
'Receta tradicional valenciana con pollo, conejo y verduras.',
17.50,
20,
'RICE',
true,
NOW(),
'https://recetaselite.com/wp-content/uploads/2022/08/paella-valenciana-tradicional.png'
),

(
'Arroz Negro',
'Arroz negro con sepia y alioli suave.',
18.20,
25,
'RICE',
true,
NOW(),
'https://www.recetassinlactosa.com/wp-content/uploads/2015/08/Arroz-negro-1-1024x683.jpg.webp'
),

(
'Arroz Meloso de Carabineros',
'Arroz meloso elaborado con fondo concentrado y carabineros.',
22.90,
15,
'RICE',
true,
NOW(),
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqY-Zr1W6cy-2F72cS7h65uBYEEfgHC_3lLzUFwmtl1isbHNUq1xXQC3Y7&s=10'
),

(
'Arroz Caldoso de Bogavante',
'Arroz caldoso con bogavante nacional.',
24.90,
12,
'RICE',
true,
NOW(),
'https://losarrocesdekiko.com/wp-content/uploads/2022/04/cua-scaled.jpg'
),

(
'Risotto de Setas',
'Risotto cremoso de boletus y parmesano.',
18.90,
20,
'RICE',
true,
NOW(),
'https://i.blogs.es/38ad9a/risotto_setas/1024_2000.jpg'
),

(
'Sushi Variado Premium',
'Selección de nigiris, makis y uramakis.',
23.50,
18,
'RICE',
true,
NOW(),
'https://elgrancatering.com/wp-content/uploads/elementor/thumbs/8274-qqdp5h7zz21agyfkqodkn5ce3no3xqsqrjf95nnfao.webp'
),

-- ============================================================================
-- STARTERS
-- ============================================================================

(
'Ensaladilla Rusa',
'Ensaladilla casera con ventresca de atún.',
7.90,
40,
'STARTER',
true,
NOW(),
'https://images.deliveryrice.com/ensaladilla.jpg'
),

(
'Croquetas de Jamón',
'Croquetas cremosas de jamón ibérico.',
8.50,
40,
'STARTER',
true,
NOW(),
'https://hips.hearstapps.com/hmg-prod/images/ensaladilla-rusa-la-bientirada-elle-gourmet-1668410033.jpg?crop=0.670xw:1.00xh;0.130xw,0&resize=980:*'
),

(
'Tortillitas de Camarones',
'Crujientes tortillitas gaditanas.',
9.20,
30,
'STARTER',
true,
NOW(),
'https://www.orientalmarket.es/recetas/wp-content/uploads/2023/12/receta-tortita-de-camarones-foto-con-mayonesa-yuzu-1024x768.jpeg'
),

(
'Calamares a la Andaluza',
'Calamares frescos fritos al momento.',
10.50,
25,
'STARTER',
true,
NOW(),
'https://www.aceitunassarasa.es/wp-content/uploads/2022/09/SARASA-REDES-AGOSTO-220032.jpg'
),

(
'Gambas al Ajillo',
'Gambas salteadas con ajo y guindilla.',
12.90,
20,
'STARTER',
true,
NOW(),
'https://i.blogs.es/377906/gambas_ajillo/1200_900.jpg'
),

(
'Pulpo a la Brasa',
'Pulpo con parmentier trufada.',
15.50,
15,
'STARTER',
true,
NOW(),
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnZKrwy5SE0RyLz5hEosqdqD45Ncs3thQSAhKBBt5LlsogSC_Av2EBGzc&s=10'
),

-- ============================================================================
-- DESSERTS
-- ============================================================================

(
'Tarta de Queso',
'Tarta de queso cremosa al horno.',
6.50,
20,
'DESSERTS',
true,
NOW(),
'https://recetasdecocina.elmundo.es/wp-content/uploads/2022/11/tarta-queso-horno-receta.jpg'
),

(
'Torrija Caramelizada',
'Torrija brioche con helado de vainilla.',
6.90,
15,
'DESSERTS',
true,
NOW(),
'https://www.gastronomiaycia.com/wp-content/uploads/2019/03/torrija_carameliz_inigolava-680x416.jpg'
),

(
'Coulant de Chocolate',
'Bizcocho de chocolate con interior fundente.',
6.80,
20,
'DESSERTS',
true,
NOW(),
'https://www.mardequel.com/110-product_medium_default/coulant-de-chocolate.jpg'
),

(
'Arroz con Leche',
'Receta tradicional con canela.',
5.90,
25,
'DESSERTS',
true,
NOW(),
'https://content-cocina.lecturas.com/medio/2018/07/19/arroz-con-leche_3bf28a85_800x800.jpg'
),

(
'Lemon Pie',
'Tarta de limón con merengue italiano.',
6.40,
18,
'DESSERTS',
true,
NOW(),
'https://content-cocina.lecturas.com/medio/2018/07/19/tarta-lemon-pie-con-merengue_43048292_800x800.jpg'
),

(
'Crema Catalana',
'Crema catalana con azúcar caramelizado.',
5.80,
20,
'DESSERTS',
true,
NOW(),
'https://assets.tmecosys.com/image/upload/t_web_rdp_recipe_584x480/img/recipe/ras/Assets/A13E0EAA-DFA7-4264-8B3B-79CCC77F9282/Derivates/B59B434D-7C1D-4EEB-9CE2-B21BD45533D9.jpg'
),

-- ============================================================================
-- DRINKS
-- ============================================================================

(
'Agua Mineral',
'Botella de agua mineral 50cl.',
2.00,
100,
'DRINK',
true,
NOW(),
'https://imagenes.elpais.com/resizer/v2/HZ5JKZ77SJLBLMWKWXIQATL24M.jpg?auth=1ea20a1f5d9dfd8bf7d0822185e702d51b1344d5f075d809a331227e8d698799&width=414'
),

(
'Coca-Cola',
'Lata de Coca-Cola 33cl.',
2.50,
100,
'DRINK',
true,
NOW(),
'https://www.confisur.es/3036-large_default/coca-cola-lata-33cl.jpg'
),

(
'Estrella Galicia',
'Cerveza Estrella Galicia 33cl.',
3.00,
100,
'DRINK',
true,
NOW(),
'https://www.viguisa.es/4036/cerveza-estrella-galicia-pack-24-latas.jpg'
),

(
'Tinto de Verano',
'Tinto de verano con limón.',
3.20,
100,
'DRINK',
true,
NOW(),
'https://static.carrefour.es/hd_510x_/img_pim_food/458644_00_1.jpg'
);