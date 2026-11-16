@echo off
setlocal
set "jogo_unidade="
for %%D in (Z Y X W V U T S R Q P O N M L K J I H G F E D C B A) do (
    if not defined jogo_unidade (
        subst %%D: "%~dp0" >nul 2>&1
        if not errorlevel 1 set "jogo_unidade=%%D:"
    )
)
if not defined jogo_unidade (
    echo Nao foi possivel criar uma unidade temporaria para iniciar o jogo.
    pause
    exit /b 1
)
pushd "%jogo_unidade%\"
if errorlevel 1 (
    subst %jogo_unidade% /d >nul 2>&1
    echo Nao foi possivel acessar a pasta do jogo pela unidade temporaria.
    pause
    exit /b 1
)
call gradlew.bat --console=plain :run
set "jogo_resultado=%errorlevel%"
popd
subst %jogo_unidade% /d >nul 2>&1
if not "%jogo_resultado%"=="0" pause
exit /b %jogo_resultado%
