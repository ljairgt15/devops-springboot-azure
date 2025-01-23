provider "azurerm" {
  features {}
}
#PROVEEDOR AZURE RESOURCE MANAGER PARA LA CREACION DE RECURSOS
/*
# Crear Grupo de recursos
resource "azurerm_resource_group" "rg" {
  name     = "devops"
  location = "eastus2"
}

# Crear Registro de contenedores
resource "azurerm_container_registry" "acr" {
  name                = "registrai"
  resource_group_name = azurerm_resource_group.rg.name
  location            = azurerm_resource_group.rg.location
  sku                 = "Basic"
  admin_enabled       = true
}
*/
# Clúster de AKS
resource "azurerm_kubernetes_cluster" "aks" {
  name                = "DevOpsAKS"
  location            = "eastus2"
  resource_group_name = "devops"
  dns_prefix          = "devops-aks"

  default_node_pool {
    name       = "default"
    node_count = 2
    vm_size    = "Standard_B2s"
  }

  identity {
    type = "SystemAssigned"
  }

  tags = {
    environment = "DevOpsChallenge"
  }
}
# Crear una instancia de API Management
resource "azurerm_api_management" "apim" {
  name                = "DevOpsAPIM"
  location            = "eastus2"
  resource_group_name = "devops"
  publisher_name      = "DevOpsTeam"
  publisher_email     = "jair.gomez@improve-it.com.ec"
  sku_name            = "Consumption" # Más económico para pruebas

  tags = {
    environment = "DevOpsChallenge"
  }
}

# Crear una API en el API Management para el balanceo
resource "azurerm_api_management_api" "api" {
  name                = "DevOpsAPI"
  resource_group_name = "devops"
  api_management_name = azurerm_api_management.apim.name
  revision            = "1"
  display_name        = "DevOps API"
  path                = "DevOps"
  protocols           = ["https"]

  import {
    content_format = "swagger-link-json"
    content_value  = "https://raw.githubusercontent.com/user/repo/master/swagger.json" # TODO JG Cambia esto si tienes un swagger local
  }
}
