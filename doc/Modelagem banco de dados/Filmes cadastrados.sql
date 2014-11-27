select
	b.nome as biblioteca,
	b.desejado,
	t.nome as tipo,
	c.nome as classificacao,
	u.nome as usuario
from biblioteca b
join usuario u on u.usuarioid = b.bibliotecaid
join classificacao_biblioteca c on c.classificacaoid = b.classificacaoid
join tipo_biblioteca t on t.tipoid = b.tipoid