## Description
기본으로 사용할 Sample Project 입니다.

## Skill
- Kotlin 
- Spring Boot
- Spring Data JPA
- H2
- MySQL

## Setting Guide
> brew/zsh 기준으로 작성되었습니다.

```shell
brew install openjdk@21

sudo ln -sfn /opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-21.jdk
echo 'export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
export CPPFLAGS="-I/opt/homebrew/opt/openjdk@21/include"

source ~/.zsrhc 
```

## Architecture
[Application Architecture](https://colosseum.atlassian.net/wiki/spaces/COLO/pages/373293082/Spec+Application+Architecture)
```
ㄴ domain
ㄴ infrastructure
ㄴ api
ㄴ usecase
ㄴ global
```
