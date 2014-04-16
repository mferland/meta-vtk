DESCRIPTION = "The Visualisation Toolkit"
HOMEPAGE = "http://www.vtk.org"
SECTION = "libs"
LICENSE = "license_vtk"
LIC_FILES_CHKSUM = "file://Copyright.txt;md5=71c9a8e9f868215797781c5bd5b254b8"

MAJ_VER = "${@oe.utils.trim_version("${PV}", 2)}"

SRC_URI = "http://www.vtk.org/files/release/${MAJ_VER}/VTK-${PV}.tar.gz"

S = "${WORKDIR}/VTK-${PV}"
