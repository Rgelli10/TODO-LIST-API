# TODO LIST

TODO LIST é uma API de cadastro, atualização e exclusão de tasks.

## AUTH para gerar token
É necessário gerar um token para cada usuário que for fazer uma requisição.
Todos os tokens expiram em 5 minutos.
É necessário passar o "username" e "pass" do usuário no body da requisição.

Os usuários com perfil "user" são: fulano@itau.com e sicrano@itau.com. Ambas as senhas são: "123456".
O usuário com o perfil "adm" é: beltrano@itau.com e a senha é: "123456".
```bash
  curl --location --request POST 'http://localhost:8080/auth' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer rm'\''!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'\''D&]{@Vr?G;2?XhbC:Qa#9#eMLN\}x3?JR3.2zr~v)gYF^8\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\J?N,nvH.<2\.r~w]*e~vgak)X"v8H`MH/7"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/' \
  --header 'Cookie: JSESSIONID=FC7373C6E153AC505A5AD38CC2F79AB9' \
  --data-raw '{
  "username": "fulano@itau.com",
  "pass": "123456"
  }
```
## GET para buscar as tasks

É necessário estar cadastrado no banco com email, senha e um token válido para poder visualizar as tasks cadastradas!
Apenas usuarios com o perfil "adm" cadastrado no banco podem visualizar todas as tasks, usuarios com o perfil "user" visualizam apenas as próprias tasks.
O usuário pode visualizar as tasks passando na url o id e o status da mesma, ou visualizar suas tasks passando na url apenas seu id cadastrado no banco.

```bash
curl --location --request GET 'http://localhost:8080/api/v1/tasks/1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmdWxhbm9AaXRhdS5jb20iLCJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY4MDQ1Nzc1LCJleHAiOjE2NjgwNDYwNzV9.aA5zQ2Aa_cSQhAWG1op8WhxSGbec-crZY0K1EqqWms4' \
--header 'Cookie: JSESSIONID=FC7373C6E153AC505A5AD38CC2F79AB9' \
--data-raw '{
"resumeDescription": "Tarefa 11",
"description": "descricao da tarefa 11"
}'
```
## POST para inserir novas tasks
Para incluir uma nova task, é necessário estar cadastrado no banco com email, senha e um token válido para poder inserir novas tasks.
No body da requisição é obrigatório apenas o resumeDescription e resume, o status não é obrigatório e como padrão setará o "PENDING".

```bash
curl --location --request POST 'http://localhost:8080/api/v1/tasks/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmdWxhbm9AaXRhdS5jb20iLCJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY4MDQ1Nzc1LCJleHAiOjE2NjgwNDYwNzV9.aA5zQ2Aa_cSQhAWG1op8WhxSGbec-crZY0K1EqqWms4' \
--header 'Cookie: JSESSIONID=FC7373C6E153AC505A5AD38CC2F79AB9' \
--data-raw '{
"resumeDescription": "Tarefa 11",
"description": "descricao da tarefa 11"
}'
```
## PUT para atualizar task
É necessário estar cadastrado no banco com email, senha e um token válido para poder atualizar as tasks já cadastradas.
É necessário passar o id da task na url para poder atualizar a mesma. 
No body da requisição é obrigatório apenas o resumeDescription e resume, o status não é obrigatório.

```bash
curl --location --request PUT 'http://localhost:8080/api/v1/tasks/12' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmdWxhbm9AaXRhdS5jb20iLCJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY4MDQ3MTQ5LCJleHAiOjE2NjgwNDc0NDl9.UHNEpBidi3JQv2pdTIW0S2hF5ubKkVPhbZL87EDUDlM' \
--header 'Cookie: JSESSIONID=FC7373C6E153AC505A5AD38CC2F79AB9' \
--data-raw '{
"resumeDescription": "Tarefa 11",
"description": "descricao da tarefa 11",
"status": "COMPLETED"
}'
```
## DELETE para excluir tasks
É necessário estar cadastrado no banco com email, senha e um token válido para poder excluir tasks.
É necessário passar o id da task url para poder excluir a mesma.

```bash
curl --location --request DELETE 'http://localhost:8080/api/v1/tasks/12' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmdWxhbm9AaXRhdS5jb20iLCJzdWIiOiJ1c2VyIiwiaWF0IjoxNjY4MDQ3MTQ5LCJleHAiOjE2NjgwNDc0NDl9.UHNEpBidi3JQv2pdTIW0S2hF5ubKkVPhbZL87EDUDlM' \
--header 'Cookie: JSESSIONID=FC7373C6E153AC505A5AD38CC2F79AB9' \
--data-raw '{
"resumeDescription": "Tarefa 11",
"description": "descricao da tarefa 11",
"status": "COMPLETED"
}'
```

## Usage

```python
As requisições só podem ser feitas apenas por usuários cadastrados no banco.
É necessário gerar um token antes de tentar realizar qualquer requisição.
Existem apenas 3 usuários cadastrados no banco, sendo dois com perfil "user" e um com perfil "adm".
```
## License

[MIT](https://choosealicense.com/licenses/mit/)