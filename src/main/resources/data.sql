
CREATE TABLE task(
id bigint PRIMARY KEY auto_increment,
title varchar(200),
description varchar(200),
points int
);


INSERT INTO task (title, description, points) values(
	'Criar Wireframe do prototipo',
	'Criar o prototipo com o figma junto com o css',
	100
);

INSERT INTO task (title, description, points) values(
	'Criar os outros metodos de PUT e DELETE da api',
	'Criando o CRUD via API rest',
	100
);


INSERT INTO task (title, description, points) values(
	'Implementar CSS criado no figma',
	'Abastecer o arquivo .css do prjeto com o css do figma',
	86
);