# Telegram Bot Example

##### This is an example of someone trying to write a bot in Spring Boot

### Examples
- registration by phone
- send message via telegram api
- bot proxy settings
- okHttp proxy settings

## Send message

```bash
curl --location --request POST 'host/v1/message' \
--header 'Content-Type: application/json' \
--data-raw '{
    "phone":"123456789",
    "content":"some message content"
}'
```
